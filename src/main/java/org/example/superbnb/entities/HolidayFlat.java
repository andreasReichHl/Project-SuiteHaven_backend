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

    @OneToMany(mappedBy = "holidayFlat", cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Bedroom> bedrooms;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE})
    private User user;

    @OneToMany(mappedBy = "holidayFlat", cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<Room> rooms;

    @OneToOne(mappedBy = "holidayFlat", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private FlatManagement management;

    public HolidayFlat() {
    }

    public HolidayFlat(String title, String city) {
        this.title = title;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFinalCleaningPrice() {
        return finalCleaningPrice;
    }

    public void setFinalCleaningPrice(int finalCleaningPrice) {
        this.finalCleaningPrice = finalCleaningPrice;
    }

    public List<Bedroom> getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(List<Bedroom> bedrooms) {
        this.bedrooms = bedrooms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public FlatManagement getManagement() {
        return management;
    }

    public void setManagement(FlatManagement management) {
        this.management = management;
    }
}
