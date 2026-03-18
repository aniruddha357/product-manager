package com.ani.pm.entity;

import jakarta.persistence.*;
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

    private String type;

    private String firstName;
    private String lastName;

    private String companyName;
    private Double annualRevenue;
}