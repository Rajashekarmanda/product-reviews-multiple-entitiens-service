package com.example.nxttrendz1.controller;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.service.ReviewJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class ReviewController {

    @Autowired
    public ReviewJpaService reviewJpaService;

    @GetMapping("products/reviews")
    public ArrayList<Review> getReviews() {
        return reviewJpaService.getReviews();
    }

    @GetMapping("products/reviews/{reviewId}")
    public Review getReview(@PathVariable("reviewId") int reviewId) {
        return reviewJpaService.getReview(reviewId);
    }

    @PostMapping("products/reviews")
    public Review addReview(@RequestBody Review review) {
        return reviewJpaService.addReview(review);
    }

    @PutMapping("products/reviews/{reviewId}")
    public Review updateReview(@PathVariable("reviewId") int reviewId, @RequestBody Review review) {
        return reviewJpaService.updateReview(reviewId, review);
    }

    @DeleteMapping("products/reviews/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") int reviewId) {
        reviewJpaService.deleteReview(reviewId);
    }

    @GetMapping("reviews/{reviewId}/product")
    public Product getReviewProductDetails(@PathVariable int reviewId) {
        return reviewJpaService.getReviewProductDetails(reviewId);
    }
}