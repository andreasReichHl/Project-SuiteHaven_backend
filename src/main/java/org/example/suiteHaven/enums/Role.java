package org.example.suiteHaven.enums;

public enum Role {
    ADMIN("ADMIN"),
    HOST("HOST"),
    USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
