package org.example.superbnb.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class HolidayFlat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String city;

    private String country;

    private String description;

    private int personNumber;

    private int price;

    private int finalCleaningPrice;

    @OneToMany(mappedBy = "holidayFlat")
    private List<SleepingRoom> sleepingRooms;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Host host;
}
