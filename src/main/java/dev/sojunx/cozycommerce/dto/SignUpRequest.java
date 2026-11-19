package dev.sojunx.cozycommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
        @Email @NotNull String email,
        @NotNull String password,
        @NotNull String confirmPassword,
        @NotNull String firstName,
        @NotNull String lastName
) {}
