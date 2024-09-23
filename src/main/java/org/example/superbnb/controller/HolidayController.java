package org.example.superbnb.controller;

import jakarta.validation.Valid;
import org.example.superbnb.dtos.holidayFlat.HolidayFlatRequestDto;
import org.example.superbnb.entities.HolidayFlat;
import org.example.superbnb.enums.ApiEnums;
import org.example.superbnb.services.HolidayFlatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEnums.SUPERBNB_API)
public class HolidayController {

    HolidayFlatService holidayFlatService;

    public HolidayController(HolidayFlatService holidayFlatService) {
        this.holidayFlatService = holidayFlatService;
    }


    @PostMapping("/newFlat")
    public ResponseEntity<HolidayFlat> createNewHolidayFlat(@RequestBody @Valid HolidayFlatRequestDto dto){
        return ResponseEntity.ok(holidayFlatService.createNewHolidayFlat(dto));
    }

}
