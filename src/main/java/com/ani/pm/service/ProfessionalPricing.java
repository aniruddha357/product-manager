package com.ani.pm.service;

import com.ani.pm.dto.Product;

public final class ProfessionalPricing implements PricingStrategy {

    private static final double ENTERPRISE_THRESHOLD = 10_000_000;

    // Java 21 record for each pricing tier
    private record Tier(double highEnd, double midRange, double laptop) {}

    private static final Tier ENTERPRISE = new Tier(1000, 550, 900);
    private static final Tier STANDARD   = new Tier(1150, 600, 1000);

    private final Tier tier;

    public ProfessionalPricing(double annualRevenue) {
        this.tier = annualRevenue > ENTERPRISE_THRESHOLD ? ENTERPRISE : STANDARD;
    }

    @Override
    public double price(Product product) {
        return switch (product) {
            case HIGH_END_PHONE  -> tier.highEnd();
            case MID_RANGE_PHONE -> tier.midRange();
            case LAPTOP          -> tier.laptop();
        };
    }
}
