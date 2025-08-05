package com.cdac.service;

import com.cdac.dao.CustomerRepository;
import com.cdac.dao.ServiceProviderRepository;
import com.cdac.dao.UserRepository;
import com.cdac.dto.AuthResponse;
import com.cdac.dto.CustomerSignUpRequest;
import com.cdac.dto.ServiceProviderSignUpRequest;
import com.cdac.dto.SignInRequest;
import com.cdac.dto.SignUpRequest;
import com.cdac.entities.Customer;
import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import com.cdac.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, 
                      CustomerRepository customerRepository,
                      ServiceProviderRepository serviceProviderRepository,
                      PasswordEncoder passwordEncoder, 
                      JwtUtil jwtUtil, 
                      AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.serviceProviderRepository = serviceProviderRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, null, null, "Email already registered", false);
        }
        if (userRepository.existsByPhoneNo(request.getPhoneNo())) {
            return new AuthResponse(null, null, null, "Phone number already registered", false);
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNo(request.getPhoneNo());
        user.setUserRole(request.getUserRole());
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getUserRole(), "Registration successful", true);
    }

    public AuthResponse customerSignUp(CustomerSignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, null, null, "Email already registered", false);
        }
        if (userRepository.existsByPhoneNo(request.getPhoneNo())) {
            return new AuthResponse(null, null, null, "Phone number already registered", false);
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNo(request.getPhoneNo());
        user.setUserRole(request.getUserRole());
        userRepository.save(user);

        // Create customer entity
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setAddress(request.getAddress());
        customer.setUser(user);
        customerRepository.save(customer);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getUserRole(), "Customer registration successful", true);
    }

    public AuthResponse serviceProviderSignUp(ServiceProviderSignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, null, null, "Email already registered", false);
        }
        if (userRepository.existsByPhoneNo(request.getPhoneNo())) {
            return new AuthResponse(null, null, null, "Phone number already registered", false);
        }
        
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNo(request.getPhoneNo());
        user.setUserRole(request.getUserRole());
        userRepository.save(user);

        // Create service provider entity with all the business information
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setServiceProviderName(request.getBusinessName());
        serviceProvider.setBusinessAddress(request.getBusinessAddress());
        serviceProvider.setServiceCategory(request.getServiceCategory());
        serviceProvider.setLicenseNumber(request.getLicenseNumber());
        serviceProvider.setBusinessDescription(request.getBusinessDescription());
        serviceProvider.setRating(0); // Default rating
        serviceProvider.setVerified(false); // Default verification status
        serviceProvider.setYearsOfExp(0); // Default experience
        serviceProvider.setUser(user);
        // Service field is nullable now, so we can save without it initially
        serviceProviderRepository.save(serviceProvider);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getUserRole(), "Service provider registration successful", true);
    }

    public AuthResponse signIn(SignInRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            if (authentication.isAuthenticated()) {
                User user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                
                // Role validation if role is specified in request
                if (request.getUserRole() != null && !user.getUserRole().equals(request.getUserRole())) {
                    return new AuthResponse(null, null, null, "Invalid role for this user", false);
                }
                
                String token = jwtUtil.generateToken(user.getEmail());
                return new AuthResponse(token, user.getEmail(), user.getUserRole(), "Login successful", true);
            } else {
                return new AuthResponse(null, null, null, "Invalid credentials", false);
            }
        } catch (Exception e) {
            return new AuthResponse(null, null, null, "Invalid credentials", false);
        }
    }
} 