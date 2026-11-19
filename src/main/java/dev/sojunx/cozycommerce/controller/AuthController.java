package dev.sojunx.cozycommerce.controller;

import dev.sojunx.cozycommerce.domain.Customer;
import dev.sojunx.cozycommerce.dto.CustomerDTO;
import dev.sojunx.cozycommerce.dto.SignInRequest;
import dev.sojunx.cozycommerce.dto.SignUpRequest;
import dev.sojunx.cozycommerce.repository.CustomerRepository;
import dev.sojunx.cozycommerce.service.CustomerService;
import dev.sojunx.cozycommerce.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository repo;
    private final JwtService jwtService;
    private final CustomerService service;

    record SignInResponse(String token, CustomerDTO customer) {}

    @PostMapping("/signin")
    ResponseEntity<SignInResponse> signin(@Valid @RequestBody SignInRequest payload) {
        var token = new UsernamePasswordAuthenticationToken(payload.email(), payload.password());
        var authentication = authenticationManager.authenticate(token);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        Customer customer = repo.findByEmail(payload.email()).orElseThrow();
        String accessToken = jwtService.generate(customer.getId());
        return ResponseEntity.ok(new SignInResponse(accessToken, CustomerDTO.from(customer)));
    }

    @PostMapping("/signup")
    ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest payload) {
        service.createCustomer(payload);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
