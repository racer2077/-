package com.example.restaurant.service;

import com.example.restaurant.dto.review.ReviewRequestDto;
import com.example.restaurant.dto.review.ReviewResponseDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review;
import com.example.restaurant.model.Visitor;
import com.example.restaurant.repository.ReviewRepository;
import com.example.restaurant.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final VisitorRepository visitorRepository;
    private final RestaurantService restaurantService;

    public ReviewService(ReviewRepository reviewRepository, VisitorRepository visitorRepository, RestaurantService restaurantService) {
        this.reviewRepository = reviewRepository;
        this.visitorRepository = visitorRepository;
        this.restaurantService = restaurantService;
    }

    public ReviewResponseDto create(ReviewRequestDto dto) {
        validate(dto);
        Visitor visitor = visitorRepository.findById(dto.visitorId()).orElseThrow();
        Restaurant restaurant = restaurantService.getEntityById(dto.restaurantId());
        Review saved = reviewRepository.save(new Review(null, visitor, restaurant, dto.rating(), dto.comment()));
        recalculateRestaurantRating(restaurant.getId());
        return toDto(saved);
    }

    public ReviewResponseDto update(Long id, ReviewRequestDto dto) {
        validate(dto);
        Review existing = reviewRepository.findById(id).orElseThrow();
        Visitor visitor = visitorRepository.findById(dto.visitorId()).orElseThrow();
        Restaurant restaurant = restaurantService.getEntityById(dto.restaurantId());
        Review saved = reviewRepository.save(new Review(id, visitor, restaurant, dto.rating(), dto.comment()));
        recalculateRestaurantRating(existing.getRestaurant().getId());
        recalculateRestaurantRating(restaurant.getId());
        return toDto(saved);
    }

    public void remove(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.deleteById(id);
        recalculateRestaurantRating(review.getRestaurant().getId());
    }

    public List<ReviewResponseDto> findAll() { return reviewRepository.findAll().stream().map(this::toDto).toList(); }
    public ReviewResponseDto findById(Long id) { return toDto(reviewRepository.findById(id).orElseThrow()); }

    private void validate(ReviewRequestDto dto) { if (dto.rating() < 1 || dto.rating() > 5) throw new IllegalArgumentException("Оценка должна быть от 1 до 5"); }
    private ReviewResponseDto toDto(Review r) { return new ReviewResponseDto(r.getId(), r.getVisitor().getId(), r.getRestaurant().getId(), r.getRating(), r.getComment()); }

    private void recalculateRestaurantRating(Long restaurantId) {
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        BigDecimal newRating = reviews.isEmpty() ? BigDecimal.ZERO : BigDecimal.valueOf(reviews.stream().mapToInt(Review::getRating).average().orElse(0.0)).setScale(2, RoundingMode.HALF_UP);
        Restaurant restaurant = restaurantService.getEntityById(restaurantId);
        restaurant.setUserRating(newRating);
        restaurantService.saveEntity(restaurant);
    }
}
