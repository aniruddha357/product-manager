package com.ani.pm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String type;

    private String firstName;
    private String lastName;

    private String companyName;

    @DecimalMin(value = "0.0", inclusive = false)
    private Double annualRevenue;
}
