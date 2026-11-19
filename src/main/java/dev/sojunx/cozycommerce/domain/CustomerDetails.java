package dev.sojunx.cozycommerce.domain;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public record CustomerDetails(Customer customer) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return customer.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    public UUID id() {return customer.getId();}

    public String email() {
        return customer.getEmail();
    }
}
