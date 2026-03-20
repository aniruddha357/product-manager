package com.ani.pm.mapper;

import com.ani.pm.dto.*;
import com.ani.pm.dto.ClientResponse;
import com.ani.pm.entity.ClientEntity;
import com.ani.pm.exception.InvalidClientTypeException;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toDto(ClientEntity e) {
        return switch (e.getType()) {
            case "INDIVIDUAL"    -> new IndividualClient(e.getId(), e.getFirstName(), e.getLastName());
            case "PROFESSIONAL"  -> new ProfessionalClient(e.getId(), e.getCompanyName(), e.getAnnualRevenue());
            default              -> throw new InvalidClientTypeException(e.getType());
        };
    }


    public ClientResponse toResponse(Client client) {
        return switch (client) {
            case IndividualClient ic   -> new ClientResponse(ic.id(), "INDIVIDUAL",
                    ic.firstName() + " " + ic.lastName());
            case ProfessionalClient pc -> new ClientResponse(pc.id(), "PROFESSIONAL",
                    pc.companyName());
        };
    }
}