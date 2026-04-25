package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review;
import com.example.restaurant.repository.RestaurantRepository;
import com.example.restaurant.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void save(Review review) {
        if (review.getRating() < 1 || review.getRating() > 5) {
            throw new IllegalArgumentException("Оценка должна быть от 1 до 5");
        }

        reviewRepository.save(review);
        recalculateRestaurantRating(review.getRestaurantId());
    }

    public void remove(Review review) {
        reviewRepository.remove(review);
        recalculateRestaurantRating(review.getRestaurantId());
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    private void recalculateRestaurantRating(Long restaurantId) {
        List<Review> restaurantReviews = reviewRepository.findAll().stream()
                .filter(r -> r.getRestaurantId().equals(restaurantId))
                .toList();

        BigDecimal newRating = restaurantReviews.isEmpty()
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(
                restaurantReviews.stream().mapToInt(Review::getRating).average().orElse(0.0)
        ).setScale(2, RoundingMode.HALF_UP);

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Ресторан не найден: " + restaurantId));
        restaurant.setUserRating(newRating);
    }
}
