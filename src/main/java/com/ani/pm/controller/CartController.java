package com.ani.pm.controller;

import com.ani.pm.dto.*;
import com.ani.pm.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{clientId}/total")
    public ResponseEntity<CartTotalResponse> total(@PathVariable Long clientId) {
        return ResponseEntity.ok(cartService.total(clientId));
    }

    @PostMapping("/client")
    public ResponseEntity<ClientCreatedResponse> createClient(
            @RequestBody @Valid CreateClientRequest request) {
        cartService.createClient(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClientCreatedResponse(request.id(), "Client created successfully"));
    }

    @PostMapping("/item")
    public ResponseEntity<ItemAddedResponse> addCartItem(
            @RequestBody @Valid AddCartItemRequest request) {
        cartService.addCartItem(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ItemAddedResponse(request.cartId(), "Cart item added"));
    }

    record ClientCreatedResponse(Long clientId, String message) {}
    record ItemAddedResponse(Long cartId, String message) {}
}