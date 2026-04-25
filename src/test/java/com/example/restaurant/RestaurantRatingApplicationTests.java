package com.example.restaurant;

import com.example.restaurant.model.CuisineType;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review;
import com.example.restaurant.model.Visitor;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.service.ReviewService;
import com.example.restaurant.service.VisitorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestaurantRatingApplicationTests {

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ReviewService reviewService;

    @Test
    void servicesShouldStoreEntitiesAndRecalculateRestaurantRating() {
        visitorService.save(new Visitor(100L, "Петр", 28, "М"));
        restaurantService.save(new Restaurant(100L, "Test Bistro", null,
                CuisineType.EUROPEAN, new BigDecimal("1500"), BigDecimal.ZERO));

        reviewService.save(new Review(100L, 100L, 5, "Супер"));
        reviewService.save(new Review(1L, 100L, 3, "Нормально"));

        Restaurant storedRestaurant = restaurantService.findAll().stream()
                .filter(r -> r.getId().equals(100L))
                .findFirst()
                .orElseThrow();

        assertThat(visitorService.findAll()).isNotEmpty();
        assertThat(reviewService.findAll()).isNotEmpty();
        assertThat(storedRestaurant.getUserRating()).isEqualByComparingTo(new BigDecimal("4.00"));
    }
}
