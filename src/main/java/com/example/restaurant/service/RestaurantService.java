package com.example.restaurant.service;

import com.example.restaurant.dto.restaurant.RestaurantRequestDto;
import com.example.restaurant.dto.restaurant.RestaurantResponseDto;
import com.example.restaurant.model.Restaurant;
import com.example.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    public RestaurantService(RestaurantRepository restaurantRepository) { this.restaurantRepository = restaurantRepository; }

    public RestaurantResponseDto create(RestaurantRequestDto dto) { validate(dto); return toDto(restaurantRepository.save(new Restaurant(null, dto.name(), dto.description(), dto.cuisineType(), dto.averageCheck(), BigDecimal.ZERO))); }
    public RestaurantResponseDto update(Long id, RestaurantRequestDto dto) {
        validate(dto);
        Restaurant old = restaurantRepository.findById(id).orElseThrow();
        return toDto(restaurantRepository.save(new Restaurant(id, dto.name(), dto.description(), dto.cuisineType(), dto.averageCheck(), old.getUserRating())));
    }
    public void remove(Long id) { restaurantRepository.deleteById(id); }
    public List<RestaurantResponseDto> findAll() { return restaurantRepository.findAll().stream().map(this::toDto).toList(); }
    public RestaurantResponseDto findById(Long id) { return toDto(restaurantRepository.findById(id).orElseThrow()); }
    public Restaurant getEntityById(Long id) { return restaurantRepository.findById(id).orElseThrow(); }
    public void saveEntity(Restaurant restaurant) { restaurantRepository.save(restaurant); }
    private void validate(RestaurantRequestDto dto) { if (dto.name() == null || dto.cuisineType() == null || dto.averageCheck() == null) throw new IllegalArgumentException("name, cuisineType, averageCheck обязательны"); }
    private RestaurantResponseDto toDto(Restaurant r) { return new RestaurantResponseDto(r.getId(), r.getName(), r.getDescription(), r.getCuisineType(), r.getAverageCheck(), r.getUserRating()); }
}
