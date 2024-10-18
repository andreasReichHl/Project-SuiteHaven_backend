package org.example.suiteHaven.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        String firstname,
        String lastname,
        @Email
        String email,
        @NotNull
        String password
) {
}
