package com.cdac.controller;

import com.cdac.entities.Booking;
import com.cdac.entities.Customer;
import com.cdac.serviceEntity.CustomerService;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "APIs for customer operations")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/profile")
    @Operation(summary = "Get customer profile")
    public ResponseEntity<Customer> getProfile() {
        String userEmail = getCurrentUserEmail();
        Customer customer = customerService.getCustomerProfile(userEmail);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/bookings")
    @Operation(summary = "Get customer bookings")
    public ResponseEntity<List<Booking>> getBookings() {
        String userEmail = getCurrentUserEmail();
        List<Booking> bookings = customerService.getCustomerBookings(userEmail);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/book-service")
    @Operation(summary = "Book a service")
    public ResponseEntity<Booking> bookService(@RequestParam Long cartId) {
        String userEmail = getCurrentUserEmail();
        Booking booking = customerService.bookService(userEmail, cartId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/services")
    @Operation(summary = "Get available services")
    public ResponseEntity<String> getAvailableServices() {
        String userEmail = getCurrentUserEmail();
        String services = customerService.getAvailableServices(userEmail);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/feedback")
    @Operation(summary = "Submit feedback")
    public ResponseEntity<String> submitFeedback(@RequestBody String feedback) {
        String userEmail = getCurrentUserEmail();
        String result = customerService.submitFeedback(userEmail, feedback);
        return ResponseEntity.ok(result);
    }

    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
} 