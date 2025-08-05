package com.cdac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    @GetMapping("/public")
    public ResponseEntity<Map<String, String>> publicEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "This is a public endpoint - no authentication required");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/protected")
    public ResponseEntity<Map<String, Object>> protectedEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "This is a protected endpoint");
        response.put("user", authentication.getName());
        response.put("authorities", authentication.getAuthorities());
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/admin")
    public ResponseEntity<Map<String, String>> adminEndpoint() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "This is an admin-only endpoint");
        return ResponseEntity.ok(response);
    }
} 