package com.cdac.dao;

import com.cdac.entities.Feedback;
import com.cdac.entities.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
    List<Feedback> findByServiceProvider(ServiceProvider serviceProvider);
    
    List<Feedback> findByServiceProviderOrderByRatingDesc(ServiceProvider serviceProvider);
    
    List<Feedback> findByRatingGreaterThanEqual(double minRating);
} 