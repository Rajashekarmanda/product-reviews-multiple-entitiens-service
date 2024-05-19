package com.example.nxttrendz1.service;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.model.Review;
import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class ReviewJpaService implements ReviewRepository {
	@Autowired
	ReviewJpaRepository reviewJpaRepository;

	@Override
	public ArrayList<Review> getReviews() {
		List<Review> reviewsList = reviewJpaRepository.findAll();
		ArrayList<Review> reviews = new ArrayList<>(reviewsList);
		return reviews;
	}

	@Override
	public Review getReview(int reviewId) {
		try {
			Review foundReview = reviewJpaRepository.findById(reviewId).get();
			return foundReview;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found review with this ID");
		}
	}

	@Override
	public Review addReview(Review review) {
		try {
			reviewJpaRepository.save(review);
			return review;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Review updateReview(int reviewId, Review review) {
		try {
			Review reviewFound = reviewJpaRepository.findById(reviewId).get();
			if (review.getReviewContent() != null) {
				reviewFound.setReviewContent(review.getReviewContent());
			}
			reviewJpaRepository.save(reviewFound);
			return reviewFound;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid reviewId");
		}
	}

	@Override
	public void deleteReview(int reviewId) {
		try {
			reviewJpaRepository.deleteById(reviewId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid reviewId");
		}

	}

	@Override
	public Product getReviewProductDetails(int reviewId) {
		try {
			Review reviewDetails = reviewJpaRepository.findById(reviewId).get();
			Product productDetails = reviewDetails.getProduct();
			return productDetails;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}