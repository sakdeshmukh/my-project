package com.cdac.dao;

import com.cdac.entities.Booking;
import com.cdac.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByCustomer(Customer customer);
    
    List<Booking> findByBookingDate(LocalDate bookingDate);
    
    List<Booking> findByCustomerOrderByBookingDateDesc(Customer customer);
    
    List<Booking> findByTotalAmountGreaterThan(double amount);
} 