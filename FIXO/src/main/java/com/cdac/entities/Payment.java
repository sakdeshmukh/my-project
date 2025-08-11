package com.cdac.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "payments")
@Entity
public class Payment extends BaseEntity {

//	private long paymentId;
//	private long bookingId;
	private LocalDate paymentDate;
	private double amount;
	@Enumerated(EnumType.STRING)
	@Column(name = "Payment_Status" , length = 20)
	private PaymentStatus paymentStatus;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "Payment_Mode")
	private PaymentMode paymentMode;
	@OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

}
