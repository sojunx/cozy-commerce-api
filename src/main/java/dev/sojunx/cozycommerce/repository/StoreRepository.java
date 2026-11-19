package dev.sojunx.cozycommerce.repository;

import dev.sojunx.cozycommerce.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findBySlug(String slug);

    List<Store> findAllByCustomer_Id(UUID customerId);
}