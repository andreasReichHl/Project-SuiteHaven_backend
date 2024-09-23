package org.example.superbnb.enums;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    HOST("ROLE_HOST"),
    USER("ROLE_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
