package com.cdac.service;

import com.cdac.dao.FeedbackRepository;
import com.cdac.dao.ServiceProviderRepository;
import com.cdac.dao.UserRepository;
import com.cdac.entities.Feedback;
import com.cdac.entities.ServiceProvider;
import com.cdac.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final UserRepository userRepository;

    public FeedbackService(FeedbackRepository feedbackRepository,
                          ServiceProviderRepository serviceProviderRepository,
                          UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.serviceProviderRepository = serviceProviderRepository;
        this.userRepository = userRepository;
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback createFeedback(Long serviceProviderId, String userEmail, double rating, String comment) {
        // Validate rating
        if (rating < 1.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
        
        ServiceProvider serviceProvider = serviceProviderRepository.findById(serviceProviderId)
                .orElseThrow(() -> new RuntimeException("Service provider not found"));
        
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Feedback feedback = new Feedback();
        feedback.setServiceProvider(serviceProvider);
        feedback.setRating(rating);
        feedback.setComment(comment);
        feedback.setCreatedAt(LocalDateTime.now());
        
        Feedback savedFeedback = feedbackRepository.save(feedback);
        
        // Update service provider rating
        updateServiceProviderRating(serviceProvider);
        
        return savedFeedback;
    }

    public List<Feedback> getFeedbackByServiceProvider(Long serviceProviderId) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(serviceProviderId)
                .orElseThrow(() -> new RuntimeException("Service provider not found"));
        return feedbackRepository.findByServiceProviderOrderByRatingDesc(serviceProvider);
    }

    public List<Feedback> getFeedbackByRating(double minRating) {
        if (minRating < 1.0 || minRating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
        return feedbackRepository.findByRatingGreaterThanEqual(minRating);
    }

    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    public void deleteFeedback(Long feedbackId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        
        // Only allow deletion if user is admin or the feedback creator
        // For now, we'll allow deletion for simplicity
        ServiceProvider serviceProvider = feedback.getServiceProvider();
        feedbackRepository.delete(feedback);
        
        // Update service provider rating after deletion
        updateServiceProviderRating(serviceProvider);
    }

    private void updateServiceProviderRating(ServiceProvider serviceProvider) {
        List<Feedback> feedbacks = feedbackRepository.findByServiceProvider(serviceProvider);
        if (!feedbacks.isEmpty()) {
            double averageRating = feedbacks.stream()
                    .mapToDouble(Feedback::getRating)
                    .average()
                    .orElse(0.0);
            serviceProvider.setRating((int) Math.round(averageRating));
            serviceProviderRepository.save(serviceProvider);
        } else {
            // If no feedbacks, set rating to 0
            serviceProvider.setRating(0);
            serviceProviderRepository.save(serviceProvider);
        }
    }
} 