package com.ani.pm.service;

import com.ani.pm.dto.Client;
import com.ani.pm.dto.IndividualClient;
import com.ani.pm.dto.ProfessionalClient;
import com.ani.pm.dto.ShoppingCart;

public class CartCalculator {

    public double calculate(Client client, ShoppingCart cart) {

        var strategy = switch (client) {
            case IndividualClient ignored -> new IndividualPricing();
            case ProfessionalClient pc -> new ProfessionalPricing(pc.annualRevenue());
        };

        return cart.items().stream()
                .mapToDouble(i -> strategy.price(i.product()) * i.quantity())
                .sum();
    }
}
