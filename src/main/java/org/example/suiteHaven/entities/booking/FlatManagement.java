package org.example.suiteHaven.entities.booking;

import jakarta.persistence.*;
import org.example.suiteHaven.entities.flat.HolidayFlat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
public class FlatManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime lastUpdate;

    @Column
    private boolean isPublic;

    @Column
    private boolean isActive;

    @OneToOne()
    private HolidayFlat holidayFlat;

    @OneToMany(mappedBy = "flatManagement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Evaluation> evaluations = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.created = LocalDateTime.now();
        this.lastUpdate = LocalDateTime.now();
        this.isPublic = false;
        this.isActive = true;
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastUpdate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public HolidayFlat getHolidayFlat() {
        return holidayFlat;
    }

    public void setHolidayFlat(HolidayFlat holidayFlat) {
        this.holidayFlat = holidayFlat;
    }

    public Set<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Set<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public int getEvaluationCount(){
        return evaluations.size();
    }

    public double getAverageEvaluation(){
       double avg = evaluations.stream()
                .mapToDouble(Evaluation::getAverageStars)
                .average().orElse(0.0);
       return (double) Math.round(avg * 10) / 10.0;
    }
}
