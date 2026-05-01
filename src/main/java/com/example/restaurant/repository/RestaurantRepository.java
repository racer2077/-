package com.example.restaurant.repository;

import com.example.restaurant.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    public void save(Restaurant restaurant) {
        restaurants.removeIf(r -> r.getId().equals(restaurant.getId()));
        restaurants.add(restaurant);
    }

    public void remove(Long id) {
        restaurants.removeIf(r -> r.getId().equals(id));
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurants.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
