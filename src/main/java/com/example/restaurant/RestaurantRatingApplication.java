package com.example.restaurant;

import com.example.restaurant.dto.restaurant.RestaurantRequestDto;
import com.example.restaurant.dto.review.ReviewRequestDto;
import com.example.restaurant.dto.user.UserRequestDto;
import com.example.restaurant.model.CuisineType;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.service.ReviewService;
import com.example.restaurant.service.VisitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class RestaurantRatingApplication {
    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    public RestaurantRatingApplication(VisitorService visitorService, RestaurantService restaurantService, ReviewService reviewService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantRatingApplication.class, args);
    }

    @PostConstruct
    public void seedData() {
        visitorService.create(new UserRequestDto("Анна", 25, "Ж"));
        visitorService.create(new UserRequestDto(null, 30, "М"));
        restaurantService.create(new RestaurantRequestDto("La Pasta", "Итальянская классика", CuisineType.ITALIAN, new BigDecimal("2200")));
        restaurantService.create(new RestaurantRequestDto("Dragon Wok", "", CuisineType.CHINESE, new BigDecimal("1700")));
        reviewService.create(new ReviewRequestDto(1L, 1L, 5, "Отлично!"));
        reviewService.create(new ReviewRequestDto(2L, 1L, 4, "Хорошо"));
    }
}
