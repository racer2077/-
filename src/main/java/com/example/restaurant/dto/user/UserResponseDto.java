package com.example.restaurant.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные посетителя")
public record UserResponseDto(Long id, String name, Integer age, String gender) {
}
