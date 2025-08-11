package com.cdac.controller;

import com.cdac.entities.Category;
import com.cdac.entities.Service;
import com.cdac.entities.User;
import com.cdac.serviceEntity.SharedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shared")
@Tag(name = "Shared", description = "APIs accessible by multiple roles")
public class SharedController {

    private final SharedService sharedService;

    public SharedController(SharedService sharedService) {
        this.sharedService = sharedService;
    }

    @GetMapping("/services")
    @Operation(summary = "Get all services")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SERVICEPROVIDER', 'ADMIN')")
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = sharedService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/bookings")
    @Operation(summary = "Get bookings")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SERVICEPROVIDER', 'ADMIN')")
    public ResponseEntity<String> getBookings() {
        String userEmail = getCurrentUserEmail();
        String bookings = sharedService.getBookings(userEmail);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/profile")
    @Operation(summary = "Get user profile")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SERVICEPROVIDER', 'ADMIN')")
    public ResponseEntity<User> getProfile() {
        String userEmail = getCurrentUserEmail();
        User user = sharedService.getUserProfile(userEmail);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    @Operation(summary = "Update user profile")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SERVICEPROVIDER', 'ADMIN')")
    public ResponseEntity<User> updateProfile(@RequestBody User updatedUser) {
        String userEmail = getCurrentUserEmail();
        User user = sharedService.updateUserProfile(userEmail, updatedUser);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/categories")
    @Operation(summary = "Get service categories")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SERVICEPROVIDER', 'ADMIN')")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = sharedService.getCategories();
        return ResponseEntity.ok(categories);
    }

    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
} 