package dev.sojunx.cozycommerce.controller;

import dev.sojunx.cozycommerce.domain.CustomerDetails;
import dev.sojunx.cozycommerce.domain.Store;
import dev.sojunx.cozycommerce.dto.StoreDTO;
import dev.sojunx.cozycommerce.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreRepository repo;

    @GetMapping("/{slug}")
    ResponseEntity<StoreDTO> getStoreBySlug(@PathVariable String slug) {
        Store store = repo.findBySlug(slug).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "store not found"));

        return ResponseEntity.ok(StoreDTO.from(store));
    }

    @GetMapping
    ResponseEntity<List<StoreDTO>> getAllStore(@AuthenticationPrincipal CustomerDetails current) {
        List<Store> stores = repo.findAllByCustomer_Id(current.id());

        return ResponseEntity.ok(stores.stream().map(StoreDTO::from).toList());
    }
}
