package com.cdac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Backend is running");
        response.put("timestamp", System.currentTimeMillis());
        response.put("version", "1.0.0");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Backend is healthy and ready");
        response.put("timestamp", System.currentTimeMillis());
        response.put("version", "1.0.0");
        response.put("environment", "development");
        return ResponseEntity.ok(response);
    }
}

