package com.example.restaurant.repository;

import com.example.restaurant.model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    public void save(Review review) {
        reviews.removeIf(r -> r.getId().equals(review.getId()));
        reviews.add(review);
    }

    public void remove(Long id) {
        reviews.removeIf(r -> r.getId().equals(id));
    }

    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }

    public Optional<Review> findById(Long id) {
        return reviews.stream().filter(r -> r.getId().equals(id)).findFirst();
    }
}
