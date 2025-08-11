package com.cdac.serviceEntity;

import com.cdac.dao.BookingRepository;
import com.cdac.dao.PaymentRepository;
import com.cdac.entities.Booking;
import com.cdac.entities.Payment;
import com.cdac.entities.PaymentMode;
import com.cdac.entities.PaymentStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public Payment createPayment(Long bookingId, String paymentMode, double amount) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaymentMode(PaymentMode.valueOf(paymentMode.toUpperCase()));
        payment.setAmount(amount);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(LocalDate.now());

        return paymentRepository.save(payment);
    }

    public Payment processPayment(Long paymentId) {
        Payment payment = getPaymentById(paymentId);
        
        if (payment.getPaymentStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Payment cannot be processed. Current status: " + payment.getPaymentStatus());
        }

        // Simulate payment processing
        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        payment.setPaymentDate(LocalDate.now());
        
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    public List<Payment> getPaymentsByAmount(double minAmount) {
        return paymentRepository.findByAmountGreaterThanEqual(minAmount);
    }

    public void cancelPayment(Long paymentId) {
        Payment payment = getPaymentById(paymentId);
        
        if (payment.getPaymentStatus() == PaymentStatus.COMPLETED) {
            throw new RuntimeException("Cannot cancel a completed payment");
        }

        payment.setPaymentStatus(PaymentStatus.CANCELLED);
        paymentRepository.save(payment);
    }
} 