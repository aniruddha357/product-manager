package com.ani.pm.service;

import com.ani.pm.dto.Product;

@FunctionalInterface
public interface PricingStrategy {
    double price(Product product);
}
