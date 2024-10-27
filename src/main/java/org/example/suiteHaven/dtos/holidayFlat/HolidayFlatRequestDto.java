package org.example.suiteHaven.dtos.holidayFlat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.suiteHaven.entities.amenity.Amenity;
import org.example.suiteHaven.entities.flat.Room;
import org.example.suiteHaven.entities.flat.Bedroom;

public record HolidayFlatRequestDto(
        @NotNull
        String title,
        @NotNull
        String city,
        @NotNull
        String country,
        @NotNull
        String description,
        @Min(value = 1, message = "Die Anzahl der Personen muss größer als 0 sein")
        int person,
        @Min(value = 0, message = "Übernachtungspreis muss größer als 0 sein")
        int price,
        @Min(value = 0, message = "Reinigungspreis muss größer als 0 sein")
        int cleaning,
        Bedroom[] bedrooms,
        @NotNull
        long hostId,
        Room[] rooms,
        Amenity[] amenities
) {
}
