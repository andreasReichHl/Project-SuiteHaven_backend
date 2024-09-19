package org.example.superbnb.entities;

import jakarta.persistence.*;

@Entity
public class SleepingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String BedOne;
    private int numberBedOne;
    private String BedTwo;
    private int numberBedTwo;

    @ManyToOne
    private HolidayFlat holidayFlat;


}
