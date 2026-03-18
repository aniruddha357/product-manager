package com.ani.pm.dto;
public record CreateClientRequest(
        Long id,
        String type, // INDIVIDUAL / PROFESSIONAL
        String firstName,
        String lastName,
        String companyName,
        Double annualRevenue
) {}
