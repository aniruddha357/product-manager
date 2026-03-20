package com.ani.pm.service;

import com.ani.pm.dto.*;
import com.ani.pm.entity.*;
import com.ani.pm.exception.ClientNotFoundException;
import com.ani.pm.mapper.ClientMapper;
import com.ani.pm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final ClientRepository clientRepo;
    private final CartRepository   cartRepo;
    private final CartCalculator   calculator;
    private final ClientMapper     mapper;

    public CartTotalResponse total(Long clientId) {

        // Specific exception — mapped to 404 ProblemDetail by GlobalExceptionHandler
        var clientEntity = clientRepo.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        // Mapper handles type switch + throws InvalidClientTypeException (400)
        var client = mapper.toDto(clientEntity);

        var cartItems = cartRepo.findByClientId(clientId.toString())
                .stream()
                .map(i -> new CartItem(i.getProduct(), i.getQuantity()))
                .toList();   // Java 16+: immutable list from stream

        var total = calculator.calculate(client, new ShoppingCart(cartItems));

        // Rich response object via static factory (pattern matching inside)
        return CartTotalResponse.of(client, total);
    }

    @Transactional
    public void createClient(CreateClientRequest request) {
        // Compact constructor already validated cross-field rules — just map & save
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

    @Transactional
    public void addCartItem(AddCartItemRequest request) {
        var entity = new CartItemEntity(
                request.cartId(),
                request.product(),
                request.quantity(),
                request.clientId()
        );
        cartRepo.save(entity);
    }
}