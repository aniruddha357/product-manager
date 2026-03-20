package com.ani.pm.service;

import com.ani.pm.dto.Client;
import com.ani.pm.dto.IndividualClient;
import com.ani.pm.dto.ProfessionalClient;
import com.ani.pm.dto.ShoppingCart;

public class CartCalculator {

    public double calculate(Client client, ShoppingCart cart) {

        PricingStrategy strategy = switch (client) {
            case IndividualClient ignored          -> new IndividualPricing();
            case ProfessionalClient pc             -> new ProfessionalPricing(pc.annualRevenue());
        };

        return cart.items().stream()
                .mapToDouble(item -> strategy.price(item.product()) * item.quantity())
                .sum();
    }
}

