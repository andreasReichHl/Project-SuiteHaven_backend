package org.example.suiteHaven.enums;

import jakarta.persistence.Entity;

public enum Rooms {
    LIVINGROOM("Wohnzimmer"),
    BATH("Bad"),
    NURSERY("Kinderzimmer"),
    KITCHEN("Küche");

    private final String room;

    Rooms(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }
}
