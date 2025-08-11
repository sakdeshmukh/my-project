package com.cdac.serviceEntity;

import com.cdac.dao.CategoryRepository;
import com.cdac.dao.ServiceRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.Category;
import com.cdac.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedService {

    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;

    public SharedService(UserRepository userRepository, 
                        ServiceRepository serviceRepository, 
                        CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<com.cdac.entities.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public String getBookings(String userEmail) {
        // This would typically fetch bookings from a BookingService
        // For now, returning a placeholder message
        return "Bookings for user: " + userEmail;
    }

    public User getUserProfile(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User updateUserProfile(String userEmail, User updatedUser) {
        User existingUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Update user fields (excluding sensitive fields like password)
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNo(updatedUser.getPhoneNo());
        
        return userRepository.save(existingUser);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
} 