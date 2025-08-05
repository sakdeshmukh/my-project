package com.cdac.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class FeedbackRequest {
    
    @NotNull(message = "Service provider ID is required")
    private Long serviceProviderId;
    
    @NotNull(message = "Rating is required")
    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private Double rating;
    
    @Size(max = 500, message = "Comment must not exceed 500 characters")
    private String comment;
} 