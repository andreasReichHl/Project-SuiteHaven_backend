package org.example.superbnb.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int night;

    private int pricePerNight;

    private int finalCleaningCost;

    private int person;

    private LocalDate checkIn;

    private LocalDate checkOut;

    @ManyToOne
    private HolidayFlat holidayFlat;

    @ManyToOne
    private User user;
}
