package org.example.suiteHaven.entities.flat;

import jakarta.persistence.*;
import org.example.suiteHaven.enums.Rooms;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Enumerated(EnumType.STRING)
    private Rooms type;

    private int number;

    public Room() {
    }

    public Room(Rooms type, int number, HolidayFlat holidayFlat) {
        this.type = type;
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

    public Rooms getType() {
        return type;
    }

    public void setType(Rooms type) {
        this.type = type;
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
