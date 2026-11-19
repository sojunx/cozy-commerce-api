package dev.sojunx.cozycommerce.service;

import dev.sojunx.cozycommerce.domain.Customer;
import dev.sojunx.cozycommerce.dto.SignUpRequest;
import dev.sojunx.cozycommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;
    private final PasswordEncoder passwordEncoder;

    public void createCustomer(SignUpRequest payload) {
        if (!payload.password().equals(payload.confirmPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password not matches");

        Customer customer = new Customer();
        customer.setEmail(payload.email());
        customer.setPasswordHash(passwordEncoder.encode(payload.password()));
        customer.setFirstName(payload.firstName());
        customer.setLastName(payload.lastName());

        repo.save(customer);
    }
}
