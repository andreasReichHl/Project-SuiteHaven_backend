package org.example.suiteHaven.entities.flat;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bedroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private HolidayFlat holidayFlat;

    @Column(nullable = false)
    private int roomNumber;

   @OneToMany(mappedBy = "bedroom", cascade = {
           CascadeType.MERGE,
           CascadeType.PERSIST,
            CascadeType.REMOVE
   }, fetch = FetchType.LAZY)
    private List<Bed> beds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HolidayFlat getHolidayFlat() {
        return holidayFlat;
    }

    public void setHolidayFlat(HolidayFlat holidayFlat) {
        this.holidayFlat = holidayFlat;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }
}
