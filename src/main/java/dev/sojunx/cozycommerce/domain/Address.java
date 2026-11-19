package dev.sojunx.cozycommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String addressLine;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean isDefault = false;
}
