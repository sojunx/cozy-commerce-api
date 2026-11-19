package dev.sojunx.cozycommerce.controller;

import dev.sojunx.cozycommerce.domain.CustomerDetails;
import dev.sojunx.cozycommerce.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    @GetMapping("/me")
    ResponseEntity<CustomerDTO> me(@AuthenticationPrincipal CustomerDetails current) {
        return ResponseEntity.ok(CustomerDTO.from(current.customer()));
    }
}
