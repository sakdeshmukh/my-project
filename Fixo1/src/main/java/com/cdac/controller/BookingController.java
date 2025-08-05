package com.cdac.controller;

import com.cdac.dto.BookingRequest;
import com.cdac.entities.Booking;
import com.cdac.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    @Operation(summary = "Get all bookings (Admin only)")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/my-bookings")
    @Operation(summary = "Get customer's bookings")
    public ResponseEntity<List<Booking>> getMyBookings(Authentication authentication) {
        String userEmail = authentication.getName();
        List<Booking> bookings = bookingService.getBookingsByCustomer(userEmail);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{bookingId}")
    @Operation(summary = "Get booking by ID")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new booking")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        Booking booking = bookingService.createBooking(userEmail, request.getCartId());
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/by-date")
    @Operation(summary = "Get bookings by date")
    public ResponseEntity<List<Booking>> getBookingsByDate(@RequestParam String date) {
        LocalDate bookingDate = LocalDate.parse(date);
        List<Booking> bookings = bookingService.getBookingsByDate(bookingDate);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{bookingId}")
    @Operation(summary = "Cancel a booking")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId, Authentication authentication) {
        String userEmail = authentication.getName();
        bookingService.cancelBooking(bookingId, userEmail);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
} 