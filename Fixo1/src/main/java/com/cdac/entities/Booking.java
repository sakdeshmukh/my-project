package com.cdac.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
