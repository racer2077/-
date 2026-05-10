package com.example.restaurant.dto.restaurant;

import com.example.restaurant.model.CuisineType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Данные ресторана")
public record RestaurantResponseDto(Long id,
                                    String name,
                                    String description,
                                    CuisineType cuisineType,
                                    BigDecimal averageCheck,
                                    BigDecimal userRating) {
}
