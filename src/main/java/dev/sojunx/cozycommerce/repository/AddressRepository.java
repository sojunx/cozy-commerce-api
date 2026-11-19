package dev.sojunx.cozycommerce.repository;

import dev.sojunx.cozycommerce.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByCustomer_Id(UUID customerId);
}
