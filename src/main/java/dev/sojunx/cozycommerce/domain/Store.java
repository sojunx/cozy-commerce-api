package dev.sojunx.cozycommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity(name = "stores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Store {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
