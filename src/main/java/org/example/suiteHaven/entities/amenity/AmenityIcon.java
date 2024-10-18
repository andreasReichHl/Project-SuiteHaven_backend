package org.example.suiteHaven.entities.amenity;

import jakarta.persistence.*;
import org.example.suiteHaven.enums.AmenityType;

@Entity
public class AmenityIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    private AmenityType amenityType;

    public AmenityIcon() {
    }

    public AmenityIcon(String url, AmenityType amenityType) {
        this.url = url;
        this.amenityType = amenityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AmenityType getAmenityType() {
        return amenityType;
    }

    public void setAmenityType(AmenityType amenityType) {
        this.amenityType = amenityType;
    }
}
