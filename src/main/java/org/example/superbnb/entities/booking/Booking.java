package org.example.superbnb.entities.booking;

import jakarta.persistence.*;
import org.example.superbnb.entities.flat.HolidayFlat;
import org.example.superbnb.entities.users.User;

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
