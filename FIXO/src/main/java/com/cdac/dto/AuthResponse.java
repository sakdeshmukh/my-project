package com.cdac.dto;

import com.cdac.entities.UserRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String email;
    private UserRole userRole;
    private String message;
    private boolean success;
    
    // Manual constructor and getters/setters for Lombok compatibility
    public AuthResponse() {}
    
    public AuthResponse(String token, String email, UserRole userRole, String message, boolean success) {
        this.token = token;
        this.email = email;
        this.userRole = userRole;
        this.message = message;
        this.success = success;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public UserRole getUserRole() {
        return userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
} 