package com.ani.pm.controller;

import com.ani.pm.dto.AddCartItemRequest;
import com.ani.pm.dto.CreateClientRequest;
import com.ani.pm.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{clientId}/total")
    public double total(@PathVariable Long clientId) {
        return cartService.total(clientId);
    }

    @PostMapping("/client")
    public String createClient(@RequestBody CreateClientRequest request) {
        cartService.createClient(request);
        return "Client created successfully";
    }

    @PostMapping("/item")
    public String addCartItem(@RequestBody AddCartItemRequest request) {
        cartService.addCartItem(request);
        return "Cart item added";
    }
}
