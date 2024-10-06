package org.example.superbnb.controller;

import org.example.superbnb.dtos.photos.ImgBBDto;
import org.example.superbnb.dtos.photos.PhotoRequestDto;
import org.example.superbnb.entities.flat.Photo;
import org.example.superbnb.repositories.PhotoRepository;
import org.example.superbnb.services.PhotoService;
import org.springframework.web.bind.annotation.*;

import static org.example.superbnb.enums.ApiEnums.SUPERBNB_API;

@RestController
@RequestMapping(SUPERBNB_API + "/photo")
public class PhotoController {

    final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public void addPhoto(@ModelAttribute PhotoRequestDto dto) throws Exception{
        try {
            photoService.addNewPhoto(dto);
        }catch (Exception e){
            throw new Exception("Fehler:" + e.getMessage());
        }
    }
}
