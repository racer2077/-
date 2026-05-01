package com.example.restaurant.service;

import com.example.restaurant.dto.review.ReviewRequestDto;
import com.example.restaurant.dto.review.ReviewResponseDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public ReviewResponseDto create(ReviewRequestDto dto) {
        validate(dto);
        Review review = new Review(idGenerator.incrementAndGet(), dto.visitorId(), dto.restaurantId(), dto.rating(), dto.comment());
        reviewRepository.save(review);
        recalculateRestaurantRating(dto.restaurantId());
        return toDto(review);
    }

    public ReviewResponseDto update(Long id, ReviewRequestDto dto) {
        validate(dto);
        Review existing = reviewRepository.findById(id).orElseThrow();
        Long oldRestaurantId = existing.getRestaurantId();
        Review updated = new Review(id, dto.visitorId(), dto.restaurantId(), dto.rating(), dto.comment());
        reviewRepository.save(updated);
        recalculateRestaurantRating(oldRestaurantId);
        recalculateRestaurantRating(dto.restaurantId());
        return toDto(updated);
    }

    public void remove(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.remove(id);
        recalculateRestaurantRating(review.getRestaurantId());
    }

    public List<ReviewResponseDto> findAll() { return reviewRepository.findAll().stream().map(this::toDto).toList(); }
    public ReviewResponseDto findById(Long id) { return toDto(reviewRepository.findById(id).orElseThrow()); }

    private void validate(ReviewRequestDto dto) {
        if (dto.rating() < 1 || dto.rating() > 5) throw new IllegalArgumentException("Оценка должна быть от 1 до 5");
    }

    private ReviewResponseDto toDto(Review r) { return new ReviewResponseDto(r.getId(), r.getVisitorId(), r.getRestaurantId(), r.getRating(), r.getComment()); }

    private void recalculateRestaurantRating(Long restaurantId) {
        List<Review> restaurantReviews = reviewRepository.findAll().stream().filter(r -> r.getRestaurantId().equals(restaurantId)).toList();
        BigDecimal newRating = restaurantReviews.isEmpty() ? BigDecimal.ZERO : BigDecimal.valueOf(restaurantReviews.stream().mapToInt(Review::getRating).average().orElse(0.0)).setScale(2, RoundingMode.HALF_UP);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        restaurant.setUserRating(newRating);
    }
}
