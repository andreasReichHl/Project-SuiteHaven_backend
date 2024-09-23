package org.example.superbnb.entities;

import jakarta.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int number;

    public Room() {
    }

    public Room(String name, int number, HolidayFlat holidayFlat) {
        this.name = name;
        this.number = number;
        this.holidayFlat = holidayFlat;
    }

    @ManyToOne
    private HolidayFlat holidayFlat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public HolidayFlat getHolidayFlat() {
        return holidayFlat;
    }

    public void setHolidayFlat(HolidayFlat holidayFlat) {
        this.holidayFlat = holidayFlat;
    }
}
