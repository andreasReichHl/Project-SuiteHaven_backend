package org.example.superbnb.dtos.bedroom;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.superbnb.enums.BedType;

public record BedRequestDto(
        @NotNull(message = "Bettentyp darf nicht leer sein!")
        BedType bedType,

        @Min(value = 1, message = "Die Anzahl der Betten muss mindestens 1 sein")
        int quantity
) {
}