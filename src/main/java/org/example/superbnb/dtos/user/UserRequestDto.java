package org.example.superbnb.dtos.user;

public record UserRequestDto(
        String firstname,
        String lastname,
        String email,
        String password
) {
}
