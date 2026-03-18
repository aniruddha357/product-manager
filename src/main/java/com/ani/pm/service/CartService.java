package com.ani.pm.service;


import com.ani.pm.dto.*;
import com.ani.pm.entity.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ani.pm.repository.CartRepository;
import com.ani.pm.repository.ClientRepository;


@Service
@RequiredArgsConstructor
public class CartService {

    private final ClientRepository clientRepo;
    private final CartRepository cartRepo;
    private final CartCalculator calculator = new CartCalculator();

    public double total(Long clientId) {

        var clientEntity = clientRepo.findById(clientId)
                .orElseThrow();

        var items = cartRepo.findByClientId(clientId.toString());

        var client = switch (clientEntity.getType()) {
            case "INDIVIDUAL" ->
                    new IndividualClient(clientEntity.getId(), clientEntity.getFirstName(), clientEntity.getLastName());

            case "PROFESSIONAL" ->
                    new ProfessionalClient(clientEntity.getId(), clientEntity.getCompanyName(), clientEntity.getAnnualRevenue());

            default -> throw new IllegalStateException("Invalid type");
        };

        var cartItems = items.stream()
                .map(i -> new CartItem(i.getProduct(), i.getQuantity()))
                .toList();

        return calculator.calculate(client, new ShoppingCart(cartItems));
    }

    public void createClient(CreateClientRequest request) {
        var entity = new com.ani.pm.entity.ClientEntity(
                request.id(),
                request.type(),
                request.firstName(),
                request.lastName(),
                request.companyName(),
                request.annualRevenue()
        );

        clientRepo.save(entity);
    }

    public void addCartItem(AddCartItemRequest request) {

        var entity = new CartItemEntity();
        entity.setId(request.cartId());
        entity.setClientId(request.clientId());
        entity.setProduct(request.product());
        entity.setQuantity(request.quantity());

        cartRepo.save(entity);
    }
}
