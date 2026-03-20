package com.ani.pm.entity;

import com.ani.pm.dto.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Product product;

    @Positive
    @Column(nullable = false)
    private int quantity;

    @NotBlank
    @Column(nullable = false, name = "client_id")
    private String clientId;
}