package com.cdac.serviceEntity;

import com.cdac.dao.BookingRepository;
import com.cdac.dao.CartRepository;
import com.cdac.dao.CustomerRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.Booking;
import com.cdac.entities.Cart;
import com.cdac.entities.Customer;
import com.cdac.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public BookingService(BookingRepository bookingRepository,
                         CustomerRepository customerRepository,
                         UserRepository userRepository,
                         CartRepository cartRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByCustomer(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return bookingRepository.findByCustomerOrderByBookingDateDesc(customer);
    }

    public Booking createBooking(String userEmail, Long cartId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        if (!cart.getCustomer().equals(customer)) {
            throw new RuntimeException("Cart does not belong to this customer");
        }
        
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCart(cart);
        booking.setBookingDate(LocalDate.now());
        booking.setTotalAmount(cart.getTotalAmount());
        
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public List<Booking> getBookingsByDate(LocalDate date) {
        return bookingRepository.findByBookingDate(date);
    }

    public void cancelBooking(Long bookingId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        if (!booking.getCustomer().equals(customer)) {
            throw new RuntimeException("Booking does not belong to this customer");
        }
        
        bookingRepository.delete(booking);
    }
} 