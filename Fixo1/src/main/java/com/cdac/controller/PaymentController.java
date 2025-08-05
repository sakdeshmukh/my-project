package com.cdac.controller;

import com.cdac.dto.PaymentRequest;
import com.cdac.entities.Payment;
import com.cdac.entities.PaymentStatus;
import com.cdac.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment Management", description = "APIs for managing payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @Operation(summary = "Get all payments (Admin only)")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Get payment by ID")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new payment")
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.createPayment(request.getBookingId(), request.getPaymentMode(), request.getAmount());
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/{paymentId}/process")
    @Operation(summary = "Process a payment")
    public ResponseEntity<Payment> processPayment(@PathVariable Long paymentId) {
        Payment payment = paymentService.processPayment(paymentId);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/by-status")
    @Operation(summary = "Get payments by status")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@RequestParam PaymentStatus status) {
        List<Payment> payments = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/by-amount")
    @Operation(summary = "Get payments by minimum amount")
    public ResponseEntity<List<Payment>> getPaymentsByAmount(@RequestParam double minAmount) {
        List<Payment> payments = paymentService.getPaymentsByAmount(minAmount);
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{paymentId}")
    @Operation(summary = "Cancel a payment")
    public ResponseEntity<String> cancelPayment(@PathVariable Long paymentId) {
        paymentService.cancelPayment(paymentId);
        return ResponseEntity.ok("Payment cancelled successfully");
    }
} 