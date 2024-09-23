package org.example.superbnb.entities;

import jakarta.persistence.*;

@Entity
public class SleepingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String bedOne;  // Geändert zu CamelCase
    private int numberBedOne;
    private String bedTwo;  // Geändert zu CamelCase
    private int numberBedTwo;

    @ManyToOne
    private HolidayFlat holidayFlat;

    public SleepingRoom() {
    }

    public SleepingRoom(String bedOne, int numberBedOne, String bedTwo, int numberBedTwo) {
        this.bedOne = bedOne;
        this.numberBedOne = numberBedOne;
        this.bedTwo = bedTwo;
        this.numberBedTwo = numberBedTwo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBedOne() {
        return bedOne;
    }

    public void setBedOne(String bedOne) {
        this.bedOne = bedOne;
    }

    public int getNumberBedOne() {
        return numberBedOne;
    }

    public void setNumberBedOne(int numberBedOne) {
        this.numberBedOne = numberBedOne;
    }

    public String getBedTwo() {
        return bedTwo;
    }

    public void setBedTwo(String bedTwo) {
        this.bedTwo = bedTwo;
    }

    public int getNumberBedTwo() {
        return numberBedTwo;
    }

    public void setNumberBedTwo(int numberBedTwo) {
        this.numberBedTwo = numberBedTwo;
    }

    public HolidayFlat getHolidayFlat() {
        return holidayFlat;
    }

    public void setHolidayFlat(HolidayFlat holidayFlat) {
        this.holidayFlat = holidayFlat;
    }
}
