package com.example.restaurant.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные отзыва")
public record ReviewResponseDto(Long id, Long visitorId, Long restaurantId, int rating, String comment) {
}
