package org.example.superbnb.enums;

public enum Role {
    ADMIN("ADMIN"),
    HOST("HOST"),
    USER("USER");

    private  String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
