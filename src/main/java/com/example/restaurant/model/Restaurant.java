package com.example.restaurant.model;

import java.math.BigDecimal;

public class Restaurant {
    private Long id;
    private String name;
    private String description;
    private CuisineType cuisineType;
    private BigDecimal averageCheck;
    private BigDecimal userRating;

    public Restaurant(Long id,
                      String name,
                      String description,
                      CuisineType cuisineType,
                      BigDecimal averageCheck,
                      BigDecimal userRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.averageCheck = averageCheck;
        this.userRating = userRating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public BigDecimal getAverageCheck() {
        return averageCheck;
    }

    public BigDecimal getUserRating() {
        return userRating;
    }

    public void setUserRating(BigDecimal userRating) {
        this.userRating = userRating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cuisineType=" + cuisineType +
                ", averageCheck=" + averageCheck +
                ", userRating=" + userRating +
                '}';
    }
}
