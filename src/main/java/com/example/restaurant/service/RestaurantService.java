package com.example.restaurant.service;

import com.example.restaurant.dto.restaurant.RestaurantRequestDto;
import com.example.restaurant.dto.restaurant.RestaurantResponseDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public RestaurantService(RestaurantRepository restaurantRepository) { this.restaurantRepository = restaurantRepository; }

    public RestaurantResponseDto create(RestaurantRequestDto dto) {
        validate(dto);
        Restaurant restaurant = new Restaurant(idGenerator.incrementAndGet(), dto.name(), dto.description(), dto.cuisineType(), dto.averageCheck(), BigDecimal.ZERO);
        restaurantRepository.save(restaurant);
        return toDto(restaurant);
    }

    public RestaurantResponseDto update(Long id, RestaurantRequestDto dto) {
        validate(dto);
        Restaurant old = restaurantRepository.findById(id).orElseThrow();
        Restaurant updated = new Restaurant(id, dto.name(), dto.description(), dto.cuisineType(), dto.averageCheck(), old.getUserRating());
        restaurantRepository.save(updated);
        return toDto(updated);
    }

    public void remove(Long id) { restaurantRepository.remove(id); }
    public List<RestaurantResponseDto> findAll() { return restaurantRepository.findAll().stream().map(this::toDto).toList(); }
    public RestaurantResponseDto findById(Long id) { return toDto(restaurantRepository.findById(id).orElseThrow()); }

    private void validate(RestaurantRequestDto dto) {
        if (dto.name() == null || dto.cuisineType() == null || dto.averageCheck() == null) throw new IllegalArgumentException("name, cuisineType, averageCheck обязательны");
    }

    private RestaurantResponseDto toDto(Restaurant r) { return new RestaurantResponseDto(r.getId(), r.getName(), r.getDescription(), r.getCuisineType(), r.getAverageCheck(), r.getUserRating()); }
}
