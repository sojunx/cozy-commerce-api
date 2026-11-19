package dev.sojunx.cozycommerce.controller;

import dev.sojunx.cozycommerce.domain.Address;
import dev.sojunx.cozycommerce.domain.CustomerDetails;
import dev.sojunx.cozycommerce.dto.AddressDTO;
import dev.sojunx.cozycommerce.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressRepository repo;

    @GetMapping
    ResponseEntity<List<AddressDTO>> getAllAddress(@AuthenticationPrincipal CustomerDetails current) {
        List<Address> addresses = repo.findAllByCustomer_Id(current.id());

        return ResponseEntity.ok(addresses.stream().map(AddressDTO::from).toList());
    }
}
