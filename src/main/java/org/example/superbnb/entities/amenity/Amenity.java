package org.example.superbnb.entities.amenity;

import jakarta.persistence.*;
import org.example.superbnb.entities.flat.HolidayFlat;
import org.example.superbnb.enums.AmenityType;

@Entity

public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private AmenityType amenityType;

    @ManyToOne(fetch = FetchType.LAZY)
    private HolidayFlat holidayFlat;

    public Amenity() {
    }

    public Amenity(AmenityType amenityType, HolidayFlat holidayFlat) {
        this.amenityType = amenityType;
        this.holidayFlat = holidayFlat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AmenityType getAmenityType() {
        return amenityType;
    }

    public void setAmenityType(AmenityType amenityType) {
        this.amenityType = amenityType;
    }

    public HolidayFlat getHolidayFlat() {
        return holidayFlat;
    }

    public void setHolidayFlat(HolidayFlat holidayFlat) {
        this.holidayFlat = holidayFlat;
    }
}
