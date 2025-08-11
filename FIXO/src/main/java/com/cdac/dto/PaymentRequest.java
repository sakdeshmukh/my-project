package com.cdac.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Data
public class PaymentRequest {
    
    @NotNull(message = "Booking ID is required")
    private Long bookingId;
    
    @NotNull(message = "Payment mode is required")
    private String paymentMode;
    
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;
    
    private String cardNumber;
    
    private String cardHolderName;
    
    private String expiryDate;
    
    private String cvv;
} 