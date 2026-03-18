package com.ani.pm.dto;

public record AddCartItemRequest(
        Long cartId,
        String clientId,
        Product product,
        int quantity
) {}
