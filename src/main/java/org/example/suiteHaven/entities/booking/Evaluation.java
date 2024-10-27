package org.example.suiteHaven.entities.booking;

import jakarta.persistence.*;
import org.example.suiteHaven.entities.users.User;

import java.util.Arrays;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int cleanness;

    @Column
    private int correctInformation;

    @Column
    private int checkIn;

    @Column
    private int location;

    @Column
    private int pricePerformanceRatio;

    @Column
    private int communication;

    @Column
    private String evaluationText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flat_management")
    private FlatManagement flatManagement;

    @Transient
    private double averageStars;

    public double getAverageStars() {
        int[] ratings = {cleanness, correctInformation, checkIn, location, pricePerformanceRatio, communication};
        return Arrays.stream(ratings).average().orElse(0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCleanness() {
        return cleanness;
    }

    public void setCleanness(int cleanness) {
        this.cleanness = cleanness;
    }

    public int getCorrectInformation() {
        return correctInformation;
    }

    public void setCorrectInformation(int correctInformation) {
        this.correctInformation = correctInformation;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getPricePerformanceRatio() {
        return pricePerformanceRatio;
    }

    public void setPricePerformanceRatio(int pricePerformanceRatio) {
        this.pricePerformanceRatio = pricePerformanceRatio;
    }

    public String getEvaluationText() {
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {
        this.evaluationText = evaluationText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FlatManagement getFlatManagement() {
        return flatManagement;
    }

    public void setFlatManagement(FlatManagement flatManagement) {
        this.flatManagement = flatManagement;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }
}

