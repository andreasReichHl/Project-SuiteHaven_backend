package org.example.suiteHaven.controller;

import org.example.suiteHaven.dtos.photos.PhotoRequestDto;
import org.example.suiteHaven.services.PhotoService;
import org.springframework.web.bind.annotation.*;

import static org.example.suiteHaven.enums.ApiEnums.SUPERBNB_API;

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
