package com.cdac.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "payments")
@Entity
public class Payment extends BaseEntity {

//	private long paymentId;
//	private long bookingId;
	private LocalDate paymentDate;
	private double amount;
	@Enumerated
	@Column(name = "Payment_Status" , length = 20)
	private PaymentStatus paymentStatus;
	@Enumerated
	@Column(length = 20, name = "Payment_Mode")
	private PaymentMode paymentMode;
	@OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

}
