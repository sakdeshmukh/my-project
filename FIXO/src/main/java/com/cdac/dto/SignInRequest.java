package com.cdac.dto;

import com.cdac.entities.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    
    private UserRole userRole; // Optional role for role-based signin
    
    // Manual getters and setters for Lombok compatibility
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public UserRole getUserRole() {
        return userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
} 