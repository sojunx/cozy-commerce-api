package dev.sojunx.cozycommerce.dto;

import dev.sojunx.cozycommerce.domain.Store;

import java.time.Instant;

public record StoreDTO(
        Long id,
        String name,
        String slug,
        String description,
        Instant createdAt
) {
    public static StoreDTO from(Store entity) {
        return new StoreDTO(entity.getId(), entity.getName(), entity.getSlug(), entity.getDescription(), entity.getCreatedAt());
    }
}
