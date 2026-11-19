package dev.sojunx.cozycommerce.service;

import dev.sojunx.cozycommerce.domain.Customer;
import dev.sojunx.cozycommerce.domain.CustomerDetails;
import dev.sojunx.cozycommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final CustomerRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("customer not found"));

        return new CustomerDetails(customer);
    }

    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        Customer customer = repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("customer not found"));

        return new CustomerDetails(customer);
    }
}
