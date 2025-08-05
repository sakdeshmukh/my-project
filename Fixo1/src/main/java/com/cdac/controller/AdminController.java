package com.cdac.controller;

import com.cdac.entities.Customer;
import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import com.cdac.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "APIs for admin operations")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    @Operation(summary = "Get admin dashboard")
    public ResponseEntity<String> getDashboard() {
        String dashboard = adminService.getAdminDashboard();
        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/service-providers")
    @Operation(summary = "Get all service providers")
    public ResponseEntity<List<ServiceProvider>> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = adminService.getAllServiceProviders();
        return ResponseEntity.ok(serviceProviders);
    }

    @GetMapping("/customers")
    @Operation(summary = "Get all customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = adminService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/approve-service-provider")
    @Operation(summary = "Approve service provider")
    public ResponseEntity<String> approveServiceProvider(@RequestParam Long serviceProviderId) {
        String result = adminService.approveServiceProvider(serviceProviderId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete-user")
    @Operation(summary = "Delete user")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId) {
        String result = adminService.deleteUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reports")
    @Operation(summary = "Get system reports")
    public ResponseEntity<String> getReports() {
        String reports = adminService.getSystemReports();
        return ResponseEntity.ok(reports);
    }
} 