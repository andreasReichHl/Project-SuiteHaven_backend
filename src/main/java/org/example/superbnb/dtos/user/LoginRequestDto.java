package org.example.superbnb.dtos.user;

public record LoginRequestDto(
        String username,
        String password
) {
}
