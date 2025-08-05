package com.cdac.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookingRequest {
    
    @NotNull(message = "Cart ID is required")
    private Long cartId;
    
    private String specialInstructions;
    
    private String preferredDate;
    
    private String preferredTime;
} 