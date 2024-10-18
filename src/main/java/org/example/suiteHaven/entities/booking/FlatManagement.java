package org.example.suiteHaven.entities.booking;

import jakarta.persistence.*;
import org.example.suiteHaven.entities.flat.HolidayFlat;
import org.example.suiteHaven.enums.BookingStatus;

import java.time.LocalDate;

@Entity
public class FlatManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate created;
    private LocalDate lastUpdate;
    private boolean isPublic;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne()
    private HolidayFlat holidayFlat;

    @PrePersist
    protected void onCreate() {
        this.created = LocalDate.now();
        this.isPublic = false;
        this.isActive = true;
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastUpdate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
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
}
