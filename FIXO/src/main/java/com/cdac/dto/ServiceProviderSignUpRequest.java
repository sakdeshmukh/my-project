package com.cdac.dto;

import com.cdac.entities.UserRole;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
public class ServiceProviderSignUpRequest {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNo;
    
    @NotBlank(message = "Business name is required")
    private String businessName;
    
    @NotBlank(message = "Business address is required")
    private String businessAddress;
    
    @NotBlank(message = "Service category is required")
    private String serviceCategory;
    
    @NotBlank(message = "Service name is required")
    private String serviceName;
    
    @NotBlank(message = "Service description is required")
    private String serviceDescription;
    
    private double serviceBasePrice;
    
    @NotBlank(message = "Business description is required")
    private String businessDescription;
    
    private UserRole userRole = UserRole.SERVICEPROVIDER; // Fixed role for service provider
    
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
    
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getBusinessName() {
        return businessName;
    }
    
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    
    public String getBusinessAddress() {
        return businessAddress;
    }
    
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
    
    public String getServiceCategory() {
        return serviceCategory;
    }
    
    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getServiceBasePrice() {
        return serviceBasePrice;
    }

    public void setServiceBasePrice(double serviceBasePrice) {
        this.serviceBasePrice = serviceBasePrice;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }
    
    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }
    
    public UserRole getUserRole() {
        return userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
} 