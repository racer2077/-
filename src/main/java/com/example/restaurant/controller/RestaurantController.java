package com.example.restaurant.controller;

import com.example.restaurant.dto.restaurant.RestaurantRequestDto;
import com.example.restaurant.dto.restaurant.RestaurantResponseDto;
import com.example.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService) { this.restaurantService = restaurantService; }

    @Operation(summary = "Получить все рестораны")
    @GetMapping
    public List<RestaurantResponseDto> findAll() { return restaurantService.findAll(); }

    @Operation(summary = "Получить ресторан по id")
    @GetMapping("/{id}")
    public RestaurantResponseDto findById(@PathVariable Long id) { return restaurantService.findById(id); }

    @Operation(summary = "Создать ресторан")
    @PostMapping
    public RestaurantResponseDto create(@RequestBody RestaurantRequestDto dto) { return restaurantService.create(dto); }

    @Operation(summary = "Обновить ресторан")
    @PutMapping("/{id}")
    public RestaurantResponseDto update(@PathVariable Long id, @RequestBody RestaurantRequestDto dto) { return restaurantService.update(id, dto); }

    @Operation(summary = "Удалить ресторан")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { restaurantService.remove(id); }
}
