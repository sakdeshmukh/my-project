package com.cdac.dao;

import com.cdac.entities.Payment;
import com.cdac.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByPaymentStatus(PaymentStatus status);
    
    List<Payment> findByAmountGreaterThan(double amount);
    
    List<Payment> findByAmountGreaterThanEqual(double amount);
    
    List<Payment> findByPaymentMode(String paymentMode);
} 