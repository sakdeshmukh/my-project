package com.cdac.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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
