package com.ani.pm.service;

import com.ani.pm.dto.Product;

public interface PricingStrategy {
    double price(Product product);
}
