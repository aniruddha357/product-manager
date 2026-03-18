package com.ani.pm.service;

import com.ani.pm.dto.Product;

public class IndividualPricing implements PricingStrategy {
    public double price(Product product) {
        return switch (product) {
            case HIGH_END_PHONE -> 1500;
            case MID_RANGE_PHONE -> 800;
            case LAPTOP -> 1200;
        };
    }
}
