package org.example.suiteHaven.controller;

import jakarta.validation.Valid;
import org.example.suiteHaven.dtos.holidayFlat.HolidayFlatRequestDto;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.enums.ApiEnums;
import org.example.suiteHaven.services.HolidayFlatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${suiteHaven.api.path}" +"/flat")
public class HolidayController {

    HolidayFlatService holidayFlatService;

    public HolidayController(HolidayFlatService holidayFlatService) {
        this.holidayFlatService = holidayFlatService;
    }


    @PostMapping("/newFlat")
    public ResponseEntity<HolidayFlat> createNewHolidayFlat(@RequestBody HolidayFlatRequestDto dto, Authentication authentication){
        return ResponseEntity.ok(holidayFlatService.createNewHolidayFlat(dto, authentication));
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlat(@PathVariable long id){
        holidayFlatService.deleteFlat(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}
