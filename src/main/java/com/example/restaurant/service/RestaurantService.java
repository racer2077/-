package com.example.restaurant.service;

import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void save(Restaurant restaurant) {
        if (restaurant.getId() == null
                || restaurant.getName() == null
                || restaurant.getCuisineType() == null
                || restaurant.getAverageCheck() == null
                || restaurant.getUserRating() == null) {
            throw new IllegalArgumentException("Для ресторана обязательны id, name, cuisineType, averageCheck, userRating");
        }
        restaurantRepository.save(restaurant);
    }

    public void remove(Restaurant restaurant) {
        restaurantRepository.remove(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
