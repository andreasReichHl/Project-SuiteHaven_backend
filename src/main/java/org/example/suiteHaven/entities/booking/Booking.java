package org.example.suiteHaven.entities.booking;

import jakarta.persistence.*;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.entities.users.User;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getFinalCleaningCost() {
        return finalCleaningCost;
    }

    public void setFinalCleaningCost(int finalCleaningCost) {
        this.finalCleaningCost = finalCleaningCost;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public HolidayFlat getHolidayFlat() {
        return holidayFlat;
    }

    public void setHolidayFlat(HolidayFlat holidayFlat) {
        this.holidayFlat = holidayFlat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
