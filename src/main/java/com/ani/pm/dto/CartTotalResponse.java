package com.ani.pm.dto;

// Rich response record — tells the caller who was priced and the total
public record CartTotalResponse(
        Long clientId,
        String clientName,
        String clientType,
        double total,
        String currency
) {
    // Java 21 pattern matching in static factory
    public static CartTotalResponse of(Client client, double total) {
        // Pattern matching switch — exhaustive over sealed Client
        var name = switch (client) {
            case IndividualClient ic -> ic.firstName() + " " + ic.lastName();
            case ProfessionalClient pc -> pc.companyName();
        };
        var type = switch (client) {
            case IndividualClient ignored -> "INDIVIDUAL";
            case ProfessionalClient ignored -> "PROFESSIONAL";
        };
        return new CartTotalResponse(client.id(), name, type, total, "USD");
    }
}