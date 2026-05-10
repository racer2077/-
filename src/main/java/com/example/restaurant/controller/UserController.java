package com.example.restaurant.controller;

import com.example.restaurant.dto.user.UserRequestDto;
import com.example.restaurant.dto.user.UserResponseDto;
import com.example.restaurant.service.VisitorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final VisitorService visitorService;
    public UserController(VisitorService visitorService) { this.visitorService = visitorService; }

    @Operation(summary = "Получить всех посетителей")
    @GetMapping
    public List<UserResponseDto> findAll() { return visitorService.findAll(); }

    @Operation(summary = "Получить посетителя по id")
    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) { return visitorService.findById(id); }

    @Operation(summary = "Создать посетителя")
    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto dto) { return visitorService.create(dto); }

    @Operation(summary = "Обновить посетителя")
    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto dto) { return visitorService.update(id, dto); }

    @Operation(summary = "Удалить посетителя")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { visitorService.remove(id); }
}
