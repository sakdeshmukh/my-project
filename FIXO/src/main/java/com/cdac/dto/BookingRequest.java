package com.cdac.dto;

import lombok.Data;



@Data
public class BookingRequest {
    
    
    private Long cartId;
    
    private String specialInstructions;
    
    private String preferredDate;
    
    private String preferredTime;
} 