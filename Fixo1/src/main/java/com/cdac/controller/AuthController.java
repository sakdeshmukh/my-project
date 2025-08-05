package com.cdac.controller;

import com.cdac.dto.AuthResponse;
import com.cdac.dto.CustomerSignUpRequest;
import com.cdac.dto.ServiceProviderSignUpRequest;
import com.cdac.dto.SignInRequest;
import com.cdac.dto.SignUpRequest;
import com.cdac.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user sign up and sign in")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new user with any role")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        AuthResponse response = authService.signUp(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/signup/customer")
    @Operation(summary = "Sign up a new customer")
    public ResponseEntity<AuthResponse> customerSignUp(@Valid @RequestBody CustomerSignUpRequest request) {
        AuthResponse response = authService.customerSignUp(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/signup/service-provider")
    @Operation(summary = "Sign up a new service provider")
    public ResponseEntity<AuthResponse> serviceProviderSignUp(@Valid @RequestBody ServiceProviderSignUpRequest request) {
        AuthResponse response = authService.serviceProviderSignUp(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/signin")
    @Operation(summary = "Sign in an existing user")
    public ResponseEntity<AuthResponse> signIn(@Valid @RequestBody SignInRequest request) {
        AuthResponse response = authService.signIn(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/signin/customer")
    @Operation(summary = "Sign in as a customer")
    public ResponseEntity<AuthResponse> customerSignIn(@Valid @RequestBody SignInRequest request) {
        request.setUserRole(com.cdac.entities.UserRole.CUSTOMER);
        AuthResponse response = authService.signIn(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/signin/service-provider")
    @Operation(summary = "Sign in as a service provider")
    public ResponseEntity<AuthResponse> serviceProviderSignIn(@Valid @RequestBody SignInRequest request) {
        request.setUserRole(com.cdac.entities.UserRole.SERVICEPROVIDER);
        AuthResponse response = authService.signIn(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/signin/admin")
    @Operation(summary = "Sign in as an admin")
    public ResponseEntity<AuthResponse> adminSignIn(@Valid @RequestBody SignInRequest request) {
        request.setUserRole(com.cdac.entities.UserRole.ADMIN);
        AuthResponse response = authService.signIn(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }
} 