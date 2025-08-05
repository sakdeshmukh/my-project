package com.cdac.controller;

import com.cdac.entities.Cart;
import com.cdac.entities.CartItem;
import com.cdac.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart Management", description = "APIs for managing shopping cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "Get user's cart")
    public ResponseEntity<Cart> getCart(Authentication authentication) {
        String userEmail = authentication.getName();
        Cart cart = cartService.getOrCreateCart(userEmail);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/items")
    @Operation(summary = "Get cart items")
    public ResponseEntity<List<CartItem>> getCartItems(Authentication authentication) {
        String userEmail = authentication.getName();
        List<CartItem> items = cartService.getCartItems(userEmail);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/add")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<CartItem> addToCart(@RequestParam Long serviceProviderId,
                                             @RequestParam int quantity,
                                             Authentication authentication) {
        String userEmail = authentication.getName();
        CartItem item = cartService.addToCart(userEmail, serviceProviderId, quantity);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update")
    @Operation(summary = "Update cart item quantity")
    public ResponseEntity<String> updateCartItemQuantity(@RequestParam Long cartItemId,
                                                        @RequestParam int quantity,
                                                        Authentication authentication) {
        String userEmail = authentication.getName();
        cartService.updateCartItemQuantity(userEmail, cartItemId, quantity);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    @DeleteMapping("/remove/{cartItemId}")
    @Operation(summary = "Remove item from cart")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId, Authentication authentication) {
        String userEmail = authentication.getName();
        cartService.removeFromCart(userEmail, cartItemId);
        return ResponseEntity.ok("Item removed from cart successfully");
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Clear cart")
    public ResponseEntity<String> clearCart(Authentication authentication) {
        String userEmail = authentication.getName();
        cartService.clearCart(userEmail);
        return ResponseEntity.ok("Cart cleared successfully");
    }
} 