package dev.sojunx.cozycommerce.dto;

import dev.sojunx.cozycommerce.domain.Customer;

import java.time.Instant;
import java.util.UUID;

public record CustomerDTO(UUID id, String email, String firstName, String lastName, Instant createdAt) {
    public static CustomerDTO from(Customer entity) {
        return new CustomerDTO(entity.getId(), entity.getEmail(), entity.getFirstName(), entity.getLastName(), entity.getCreatedAt());
    }
}

