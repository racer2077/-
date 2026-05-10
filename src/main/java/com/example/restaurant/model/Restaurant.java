package com.example.restaurant.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CuisineType cuisineType;
    @Column(nullable = false)
    private BigDecimal averageCheck;
    @Column(nullable = false)
    private BigDecimal userRating;

    public Restaurant() {}

    public Restaurant(Long id, String name, String description, CuisineType cuisineType, BigDecimal averageCheck, BigDecimal userRating) {
        this.id = id; this.name = name; this.description = description; this.cuisineType = cuisineType; this.averageCheck = averageCheck; this.userRating = userRating;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public CuisineType getCuisineType() { return cuisineType; }
    public BigDecimal getAverageCheck() { return averageCheck; }
    public BigDecimal getUserRating() { return userRating; }
    public void setUserRating(BigDecimal userRating) { this.userRating = userRating; }
}
