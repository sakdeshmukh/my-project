package com.cdac.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "booking")
@Entity
public class Booking extends BaseEntity {
//	@Id
//	@GeneratedValue (strategy = GenerationType.IDENTITY)
//	private long bookingId;
//	private long cartId;
//	private long userId;
//	private long serviceProviderId;
	@Column(name = "Booking_Date")
	private LocalDate bookingDate;
	private double totalAmount;
	
	@OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;
	
	@ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
	
	

}
