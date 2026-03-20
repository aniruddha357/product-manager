package com.ani.pm.service;

import com.ani.pm.dto.Product;

public final class IndividualPricing implements PricingStrategy {

    // Java 21: private static final record for price table (value semantics)
    private record Price(double highEnd, double midRange, double laptop) {}

    private static final Price PRICES = new Price(1500, 800, 1200);

    @Override
    public double price(Product product) {
        return switch (product) {
            case HIGH_END_PHONE -> PRICES.highEnd();
            case MID_RANGE_PHONE -> PRICES.midRange();
            case LAPTOP -> PRICES.laptop();
        };
    }
}