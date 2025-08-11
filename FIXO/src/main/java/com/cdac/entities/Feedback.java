package com.cdac.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "feedback")
@Entity
public class Feedback extends BaseEntity {
	
//	private long userId;
//	private long serviceProviderId;
	private double rating;
	
	private String comment;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

}
