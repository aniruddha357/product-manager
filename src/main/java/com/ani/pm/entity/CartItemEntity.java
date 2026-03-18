package com.ani.pm.entity;

import com.ani.pm.dto.Product;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Product product;

    private int quantity;

    private String clientId;
}