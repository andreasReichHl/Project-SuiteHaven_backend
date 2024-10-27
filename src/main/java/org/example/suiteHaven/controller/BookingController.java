package org.example.suiteHaven.controller;

import org.example.suiteHaven.dtos.BookingRequestDto;
import org.example.suiteHaven.dtos.BookingResponseDto;
import org.example.suiteHaven.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${suiteHaven.api.path}" + "/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> newBooking(@RequestBody BookingRequestDto dto, Authentication authentication){
        return ResponseEntity.ok(bookingService.createNewBooking(dto, authentication));
    }
}
