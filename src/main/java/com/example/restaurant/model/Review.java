package com.example.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private int rating;
    private String comment;

    public Review() {}

    public Review(Long id, Visitor visitor, Restaurant restaurant, int rating, String comment) {
        this.id = id; this.visitor = visitor; this.restaurant = restaurant; this.rating = rating; this.comment = comment;
    }

    public Long getId() { return id; }
    public Visitor getVisitor() { return visitor; }
    public Restaurant getRestaurant() { return restaurant; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}
