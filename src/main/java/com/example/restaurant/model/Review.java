package com.example.restaurant.model;

public class Review {
    private Long visitorId;
    private Long restaurantId;
    private int rating;
    private String comment;

    public Review(Long visitorId, Long restaurantId, int rating, String comment) {
        this.visitorId = visitorId;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getVisitorId() {
        return visitorId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "visitorId=" + visitorId +
                ", restaurantId=" + restaurantId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
