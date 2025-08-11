package com.cdac.serviceEntity;

import com.cdac.dao.ServiceProviderRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;
    private final UserRepository userRepository;

    public ServiceProviderService(ServiceProviderRepository serviceProviderRepository, UserRepository userRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
        this.userRepository = userRepository;
    }

    public ServiceProvider getServiceProviderProfile(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        return serviceProviderRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Service provider profile not found"));
    }

    public List<ServiceProvider> getAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    public ServiceProvider updateServiceProviderProfile(String userEmail, ServiceProvider updatedProvider) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        ServiceProvider existingProvider = serviceProviderRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Service provider profile not found"));
        
        // Update service provider fields
        existingProvider.setServiceProviderName(updatedProvider.getServiceProviderName());
        existingProvider.setBusinessAddress(updatedProvider.getBusinessAddress());
        existingProvider.setServiceCategory(updatedProvider.getServiceCategory());
        existingProvider.setBusinessDescription(updatedProvider.getBusinessDescription());
        existingProvider.setRating(updatedProvider.getRating());
        existingProvider.setVerified(updatedProvider.isVerified());
        existingProvider.setYearsOfExp(updatedProvider.getYearsOfExp());
        
        return serviceProviderRepository.save(existingProvider);
    }

    public String getServiceProviderServices(String userEmail) {
        // This would typically fetch services from a ServiceService
        // For now, returning a placeholder message
        return "Service provider services for user: " + userEmail;
    }

    public String addService(String userEmail, String serviceDetails) {
        // This would typically create a service through a ServiceService
        // For now, returning a placeholder message
        return "Service added successfully for user: " + userEmail;
    }

    public String getServiceProviderBookings(String userEmail) {
        // This would typically fetch bookings from a BookingService
        // For now, returning a placeholder message
        return "Service provider bookings for user: " + userEmail;
    }

    public String updateService(String userEmail, String serviceDetails) {
        // This would typically update a service through a ServiceService
        // For now, returning a placeholder message
        return "Service updated successfully for user: " + userEmail;
    }

    public String removeService(String userEmail, String serviceDetails) {
        // This would typically remove a service through a ServiceService
        // For now, returning a placeholder message
        return "Service removed successfully for user: " + userEmail;
    }
} 