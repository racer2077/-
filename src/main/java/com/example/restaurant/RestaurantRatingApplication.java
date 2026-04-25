package com.example.restaurant;

import com.example.restaurant.model.CuisineType;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.model.Review;
import com.example.restaurant.model.Visitor;
import com.example.restaurant.service.RestaurantService;
import com.example.restaurant.service.ReviewService;
import com.example.restaurant.service.VisitorService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class RestaurantRatingApplication {

    private final VisitorService visitorService;
    private final RestaurantService restaurantService;
    private final ReviewService reviewService;

    public RestaurantRatingApplication(VisitorService visitorService,
                                       RestaurantService restaurantService,
                                       ReviewService reviewService) {
        this.visitorService = visitorService;
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantRatingApplication.class, args);
    }

    @PostConstruct
    public void seedData() {
        visitorService.save(new Visitor(1L, "Анна", 25, "Ж"));
        visitorService.save(new Visitor(2L, null, 30, "М"));
        visitorService.save(new Visitor(3L, "Иван", 40, "М"));

        restaurantService.save(new Restaurant(1L, "La Pasta", "Итальянская классика",
                CuisineType.ITALIAN, new BigDecimal("2200"), BigDecimal.ZERO));
        restaurantService.save(new Restaurant(2L, "Dragon Wok", "",
                CuisineType.CHINESE, new BigDecimal("1700"), BigDecimal.ZERO));

        reviewService.save(new Review(1L, 1L, 5, "Отлично!"));
        reviewService.save(new Review(2L, 1L, 4, "Хорошо"));
        reviewService.save(new Review(3L, 2L, 3, "Неплохо"));
    }

    @Bean
    public CommandLineRunner testServices() {
        return args -> {
            System.out.println("Visitors: " + visitorService.findAll());
            System.out.println("Restaurants: " + restaurantService.findAll());
            System.out.println("Reviews: " + reviewService.findAll());
        };
    }
}
