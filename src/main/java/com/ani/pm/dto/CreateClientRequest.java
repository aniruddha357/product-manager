package com.ani.pm.dto;

import jakarta.validation.constraints.*;

public record CreateClientRequest(

        Long id,

        @NotBlank(message = "Type must be INDIVIDUAL or PROFESSIONAL")
        @Pattern(regexp = "INDIVIDUAL|PROFESSIONAL",
                message = "Type must be INDIVIDUAL or PROFESSIONAL")
        String type,

        String firstName,
        String lastName,

        String companyName,

        @DecimalMin(value = "0.0", inclusive = false,
                message = "Annual revenue must be positive if provided")
        Double annualRevenue) {

    public CreateClientRequest {
        if ("INDIVIDUAL".equals(type)) {
            if (firstName == null || firstName.isBlank())
                throw new IllegalArgumentException("firstName is required for INDIVIDUAL clients");
            if (lastName == null || lastName.isBlank())
                throw new IllegalArgumentException("lastName is required for INDIVIDUAL clients");
        }
        if ("PROFESSIONAL".equals(type)) {
            if (companyName == null || companyName.isBlank())
                throw new IllegalArgumentException("companyName is required for PROFESSIONAL clients");
            if (annualRevenue == null)
                throw new IllegalArgumentException("annualRevenue is required for PROFESSIONAL clients");
        }
    }

}
