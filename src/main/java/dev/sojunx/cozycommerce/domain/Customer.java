package dev.sojunx.cozycommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Store> stores = new HashSet<>();
}
