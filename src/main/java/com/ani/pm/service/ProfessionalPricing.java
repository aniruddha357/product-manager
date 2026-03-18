package com.ani.pm.service;

import com.ani.pm.dto.Product;
import static com.ani.pm.dto.Product.*;

public class ProfessionalPricing implements PricingStrategy {

    private final double revenue;

    public ProfessionalPricing(double revenue) {
        this.revenue = revenue;
    }

    public double price(Product product) {

        return revenue > 10_000_000
                ? switch (product) {
            case HIGH_END_PHONE -> 1000;
            case MID_RANGE_PHONE -> 550;
            case LAPTOP -> 900;
        }
                : switch (product) {
            case HIGH_END_PHONE -> 1150;
            case MID_RANGE_PHONE -> 600;
            case LAPTOP -> 1000;
        };
    }
}