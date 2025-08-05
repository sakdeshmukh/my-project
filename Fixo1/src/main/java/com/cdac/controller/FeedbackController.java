package com.cdac.controller;

import com.cdac.dto.FeedbackRequest;
import com.cdac.entities.Feedback;
import com.cdac.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@Tag(name = "Feedback Management", description = "APIs for managing feedback and reviews")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @Operation(summary = "Get all feedback (Admin only)")
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{feedbackId}")
    @Operation(summary = "Get feedback by ID")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        return ResponseEntity.ok(feedback);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedbackRequest request, Authentication authentication) {
        String userEmail = authentication.getName();
        Feedback feedback = feedbackService.createFeedback(request.getServiceProviderId(), userEmail, request.getRating(), request.getComment());
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/service-provider/{serviceProviderId}")
    @Operation(summary = "Get feedback for a specific service provider")
    public ResponseEntity<List<Feedback>> getFeedbackByServiceProvider(@PathVariable Long serviceProviderId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByServiceProvider(serviceProviderId);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/by-rating")
    @Operation(summary = "Get feedback by minimum rating")
    public ResponseEntity<List<Feedback>> getFeedbackByRating(@RequestParam double minRating) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByRating(minRating);
        return ResponseEntity.ok(feedbacks);
    }

    @DeleteMapping("/{feedbackId}")
    @Operation(summary = "Delete a feedback")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId, Authentication authentication) {
        String userEmail = authentication.getName();
        feedbackService.deleteFeedback(feedbackId, userEmail);
        return ResponseEntity.ok("Feedback deleted successfully");
    }
} 