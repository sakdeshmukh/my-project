package com.cdac.controller;

import com.cdac.entities.ServiceProvider;
import com.cdac.service.ServiceProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-provider")
@Tag(name = "Service Provider", description = "APIs for service provider operations")
@PreAuthorize("hasRole('SERVICEPROVIDER')")
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @GetMapping("/profile")
    @Operation(summary = "Get service provider profile")
    public ResponseEntity<ServiceProvider> getProfile() {
        String userEmail = getCurrentUserEmail();
        ServiceProvider serviceProvider = serviceProviderService.getServiceProviderProfile(userEmail);
        return ResponseEntity.ok(serviceProvider);
    }

    @GetMapping("/services")
    @Operation(summary = "Get service provider services")
    public ResponseEntity<String> getServices() {
        String userEmail = getCurrentUserEmail();
        String services = serviceProviderService.getServiceProviderServices(userEmail);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/add-service")
    @Operation(summary = "Add a new service")
    public ResponseEntity<String> addService(@RequestBody String serviceDetails) {
        String userEmail = getCurrentUserEmail();
        String result = serviceProviderService.addService(userEmail, serviceDetails);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/bookings")
    @Operation(summary = "Get service provider bookings")
    public ResponseEntity<String> getBookings() {
        String userEmail = getCurrentUserEmail();
        String bookings = serviceProviderService.getServiceProviderBookings(userEmail);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/update-service")
    @Operation(summary = "Update service details")
    public ResponseEntity<String> updateService(@RequestBody String serviceDetails) {
        String userEmail = getCurrentUserEmail();
        String result = serviceProviderService.updateService(userEmail, serviceDetails);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove-service")
    @Operation(summary = "Remove a service")
    public ResponseEntity<String> removeService(@RequestBody String serviceDetails) {
        String userEmail = getCurrentUserEmail();
        String result = serviceProviderService.removeService(userEmail, serviceDetails);
        return ResponseEntity.ok(result);
    }

    private String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
} 