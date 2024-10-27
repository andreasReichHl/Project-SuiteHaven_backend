package org.example.suiteHaven.dtos.user;

public record UserNewResponseDto(
        String name,
        String email,
        String password
) {
}
