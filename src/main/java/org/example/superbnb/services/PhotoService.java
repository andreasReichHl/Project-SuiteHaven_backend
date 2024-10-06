package org.example.superbnb.services;

import org.example.superbnb.dtos.photos.ImgBBDto;
import org.example.superbnb.dtos.photos.PhotoRequestDto;
import org.example.superbnb.entities.flat.HolidayFlat;
import org.example.superbnb.entities.flat.Photo;
import org.example.superbnb.repositories.HolidayFlatRepository;
import org.example.superbnb.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

@Service
public class PhotoService {

    final PhotoRepository photoRepository;
    final HolidayFlatRepository holidayFlatRepository;

    public PhotoService(PhotoRepository photoRepository, HolidayFlatRepository holidayFlatRepository) {
        this.photoRepository = photoRepository;
        this.holidayFlatRepository = holidayFlatRepository;
    }

    @Value("${imgbb.key}")
    private String imgbbKey;

    @Value("${imgbb.url}")
    private String imgbbUrl;

    public void addNewPhoto(PhotoRequestDto requestDto) throws Exception{
        RestClient restClient = RestClient.create(imgbbUrl);

        HolidayFlat flat = holidayFlatRepository.findById(requestDto.holidayFlat()).orElseThrow(()-> new RuntimeException("Holiday Flat not found"));
        Arrays.stream(requestDto.file()).forEach(multipartFile ->
        {
            try {
                postPhoto(multipartFile.getResource(), restClient, flat);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        holidayFlatRepository.save(flat);
    }

    public void postPhoto(Resource file, RestClient restClient, HolidayFlat flat) throws Exception {
        MultiValueMap<String, Resource> valueMap = new LinkedMultiValueMap<>();
        valueMap.add("image", file);

        ResponseEntity<ImgBBDto> response = restClient.post()
                .uri("?key=" + imgbbKey)
                .body(valueMap)
                .retrieve()
                .toEntity(ImgBBDto.class);

        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new Exception("Image could not be saved");

        Photo photo = new Photo(Objects.requireNonNull(
                response.getBody()).data().image().filename(),
                response.getBody().data().url(),
                response.getBody().data().thumb().url()
        );

        photo.setHolidayFlat(flat);
        photoRepository.save(photo);
    }
}
