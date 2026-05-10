package com.example.restaurant.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания/обновления посетителя")
public record UserRequestDto(String name, Integer age, String gender) {
}
