package com.example.restaurant.controller;

import com.example.restaurant.dto.review.ReviewRequestDto;
import com.example.restaurant.dto.review.ReviewResponseDto;
import com.example.restaurant.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) { this.reviewService = reviewService; }

    @Operation(summary = "Получить все отзывы")
    @GetMapping
    public List<ReviewResponseDto> findAll() { return reviewService.findAll(); }

    @Operation(summary = "Получить отзыв по id")
    @GetMapping("/{id}")
    public ReviewResponseDto findById(@PathVariable Long id) { return reviewService.findById(id); }

    @Operation(summary = "Создать отзыв")
    @PostMapping
    public ReviewResponseDto create(@RequestBody ReviewRequestDto dto) { return reviewService.create(dto); }

    @Operation(summary = "Обновить отзыв")
    @PutMapping("/{id}")
    public ReviewResponseDto update(@PathVariable Long id, @RequestBody ReviewRequestDto dto) { return reviewService.update(id, dto); }

    @Operation(summary = "Удалить отзыв")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { reviewService.remove(id); }
}
