package org.example.superbnb.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.example.superbnb.enums.Role;

public record UserRequestDto(
        String firstname,
        String lastname,
        @Email
        String email,
        @NotNull
        String password
) {
}
