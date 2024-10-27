package org.example.suiteHaven.dtos;

import java.time.LocalDate;

public record BookingResponseDto(
        String flatTitle,
        LocalDate checkIn,
        LocalDate checkOut,
        long nights,
        double totalPrice
) {
}
