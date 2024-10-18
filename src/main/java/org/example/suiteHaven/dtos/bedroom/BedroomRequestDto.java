package org.example.suiteHaven.dtos.bedroom;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.suiteHaven.entities.flat.HolidayFlat;

public record BedroomRequestDto(

        @NotNull
        HolidayFlat holidayFlat,

        @Min(value = 1)
        int roomNumber,

        @Valid
        BedRequestDto[] bedRequestDtos
) {
}
