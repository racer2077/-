package com.example.restaurant.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания/обновления отзыва")
public record ReviewRequestDto(Long visitorId, Long restaurantId, int rating, String comment) {
}
