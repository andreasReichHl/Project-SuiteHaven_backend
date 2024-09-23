package org.example.superbnb.dtos.user;

public record UserNewResponseDto(
        String name,
        String email,
        String password
) {
}
