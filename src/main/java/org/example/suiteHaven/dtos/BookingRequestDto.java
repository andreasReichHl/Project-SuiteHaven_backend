package org.example.suiteHaven.dtos;

import java.time.LocalDate;

public record BookingRequestDto(
        int person,
        LocalDate checkIn,
        LocalDate checkOut,
        long flatId
) {
}
