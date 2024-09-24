package org.example.superbnb.enums;

public enum AmenityType {
    WIFI("WLAN"),
    AIR_CONDITIONING("Klimaanlage"),
    PARKING("Parkplatz"),
    KITCHEN("Küche"),
    POOL("Pool"),
    GYM("Fitnessstudio"),
    PET_FRIENDLY("Haustierfreundlich"),
    SMOKING_ALLOWED("Rauchen erlaubt"),
    LAUNDRY("Waschmaschine"),
    FREE_BREAKFAST("Frühstück inklusive"),
    SECURITY("Sicherheit"),
    FAMILY_FRIENDLY("Familienfreundlich"),
    HEATING("Heizung"),
    BALCONY("Balkon"),
    ELEVATOR("Aufzug"),
    TV("Fernseher"),
    HOT_TUB("Whirlpool"),
    COFFEE_MACHINE("Kaffeemaschine"),
    GAME_ROOM("Spielzimmer"),
    OUTDOOR_GRILL("Grillplatz");

    private final String description; // Beschreibung der Annehmlichkeit

    AmenityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}