package org.example.suiteHaven.entities.booking;

import jakarta.persistence.*;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.entities.users.User;
import org.example.suiteHaven.enums.BookingStatus;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private long night;

    @Transient
    private long totalPriceOfNight;

    private int pricePerNight;

    private int finalCleaningCost;

    private int person;

    private LocalDate checkIn;

    private LocalDate checkOut;

    @ManyToOne
    private HolidayFlat holidayFlat;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column
    private boolean isReviewed = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNight() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public long getTotalPriceOfNight() {
        return night * pricePerNight;
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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public boolean isReviewed() {
        return isReviewed;
    }

    public void setReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }
}
