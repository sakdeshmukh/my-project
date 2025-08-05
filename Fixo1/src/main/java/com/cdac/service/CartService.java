package com.cdac.service;

import com.cdac.dao.CartItemRepository;
import com.cdac.dao.CartRepository;
import com.cdac.dao.CustomerRepository;
import com.cdac.dao.ServiceProviderRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.Cart;
import com.cdac.entities.CartItem;
import com.cdac.entities.Customer;
import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    public CartService(CartRepository cartRepository,
                      CartItemRepository cartItemRepository,
                      CustomerRepository customerRepository,
                      UserRepository userRepository,
                      ServiceProviderRepository serviceProviderRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public Cart getOrCreateCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        Optional<Cart> existingCart = cartRepository.findByCustomer(customer);
        if (existingCart.isPresent()) {
            return existingCart.get();
        }
        
        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        newCart.setTotalAmount(0.0);
        return cartRepository.save(newCart);
    }

    public CartItem addToCart(String userEmail, Long serviceProviderId, int quantity) {
        Cart cart = getOrCreateCart(userEmail);
        ServiceProvider serviceProvider = serviceProviderRepository.findById(serviceProviderId)
                .orElseThrow(() -> new RuntimeException("Service provider not found"));
        
        // Check if item already exists in cart
        Optional<CartItem> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getService().equals(serviceProvider.getService()))
                .findFirst();
        
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setPrice(item.getQuantity() * serviceProvider.getService().getBasePrice());
            cartItemRepository.save(item);
            updateCartTotal(cart);
            return item;
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setService(serviceProvider.getService());
            newItem.setQuantity(quantity);
            newItem.setPrice(quantity * serviceProvider.getService().getBasePrice());
            CartItem savedItem = cartItemRepository.save(newItem);
            updateCartTotal(cart);
            return savedItem;
        }
    }

    public void removeFromCart(String userEmail, Long cartItemId) {
        Cart cart = getOrCreateCart(userEmail);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        if (!cartItem.getCart().equals(cart)) {
            throw new RuntimeException("Cart item does not belong to this customer");
        }
        
        cartItemRepository.delete(cartItem);
        updateCartTotal(cart);
    }

    public void updateCartItemQuantity(String userEmail, Long cartItemId, int quantity) {
        Cart cart = getOrCreateCart(userEmail);
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        if (!cartItem.getCart().equals(cart)) {
            throw new RuntimeException("Cart item does not belong to this customer");
        }
        
        cartItem.setQuantity(quantity);
        cartItem.setPrice(quantity * cartItem.getService().getBasePrice());
        cartItemRepository.save(cartItem);
        updateCartTotal(cart);
    }

    public void clearCart(String userEmail) {
        Cart cart = getOrCreateCart(userEmail);
        cartItemRepository.deleteAll(cart.getCartItems());
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);
    }

    public List<CartItem> getCartItems(String userEmail) {
        Cart cart = getOrCreateCart(userEmail);
        return cart.getCartItems();
    }

    private void updateCartTotal(Cart cart) {
        double total = cart.getCartItems().stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
        cart.setTotalAmount(total);
        cartRepository.save(cart);
    }
} 