package com.cdac.service;

import com.cdac.dao.CustomerRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.Booking;
import com.cdac.entities.Customer;
import com.cdac.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final BookingService bookingService;

    public CustomerService(CustomerRepository customerRepository, 
                          UserRepository userRepository,
                          BookingService bookingService) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.bookingService = bookingService;
    }

    public Customer getCustomerProfile(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer profile not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomerProfile(String userEmail, Customer updatedCustomer) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        Customer existingCustomer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer profile not found"));
        
        // Update customer fields
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        
        return customerRepository.save(existingCustomer);
    }

    public List<Booking> getCustomerBookings(String userEmail) {
        return bookingService.getBookingsByCustomer(userEmail);
    }

    public Booking bookService(String userEmail, Long cartId) {
        return bookingService.createBooking(userEmail, cartId);
    }

    public String getAvailableServices(String userEmail) {
        // This would typically fetch services from a ServiceService
        // For now, returning a placeholder message
        return "Available services for user: " + userEmail;
    }

    public String submitFeedback(String userEmail, String feedback) {
        // This would typically save feedback through a FeedbackService
        // For now, returning a placeholder message
        return "Feedback submitted successfully for user: " + userEmail;
    }
} 