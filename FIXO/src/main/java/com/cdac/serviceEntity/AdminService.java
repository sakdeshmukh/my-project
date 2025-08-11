package com.cdac.serviceEntity;

import com.cdac.dao.CustomerRepository;
import com.cdac.dao.ServiceProviderRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.Customer;
import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    public AdminService(UserRepository userRepository, 
                       CustomerRepository customerRepository, 
                       ServiceProviderRepository serviceProviderRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public String getAdminDashboard() {
        // This would typically fetch dashboard statistics
        // For now, returning a placeholder message
        return "Admin dashboard data";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<ServiceProvider> getAllServiceProviders() {
        return serviceProviderRepository.findAll();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public String approveServiceProvider(Long serviceProviderId) {
        // This would typically update the verification status
        // For now, returning a placeholder message
        return "Service provider approved: " + serviceProviderId;
    }

    public String deleteUser(Long userId) {
        // This would typically delete the user and related entities
        // For now, returning a placeholder message
        return "User deleted: " + userId;
    }

    public String getSystemReports() {
        // This would typically generate system reports
        // For now, returning a placeholder message
        return "System reports generated";
    }
} 