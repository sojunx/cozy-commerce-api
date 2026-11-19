package dev.sojunx.cozycommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(@Email @NotNull String email, @NotNull String password) {}

