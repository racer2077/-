package com.example.restaurant;

import com.example.restaurant.dto.restaurant.RestaurantRequestDto;
import com.example.restaurant.dto.review.ReviewRequestDto;
import com.example.restaurant.dto.user.UserRequestDto;
import com.example.restaurant.model.CuisineType;
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
        var user = visitorService.create(new UserRequestDto("Петр", 28, "М"));
        var restaurant = restaurantService.create(new RestaurantRequestDto("Test Bistro", null,
                CuisineType.EUROPEAN, new BigDecimal("1500")));

        reviewService.create(new ReviewRequestDto(user.id(), restaurant.id(), 5, "Супер"));
        reviewService.create(new ReviewRequestDto(1L, restaurant.id(), 3, "Нормально"));

        var storedRestaurant = restaurantService.findById(restaurant.id());

        assertThat(visitorService.findAll()).isNotEmpty();
        assertThat(reviewService.findAll()).isNotEmpty();
        assertThat(storedRestaurant.userRating()).isEqualByComparingTo(new BigDecimal("4.00"));
    }
}
