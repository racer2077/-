package com.example.restaurant.repository;

import com.example.restaurant.model.Visitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VisitorRepository {
    private final List<Visitor> visitors = new ArrayList<>();

    public void save(Visitor visitor) {
        visitors.removeIf(v -> v.getId().equals(visitor.getId()));
        visitors.add(visitor);
    }

    public void remove(Long id) {
        visitors.removeIf(v -> v.getId().equals(id));
    }

    public List<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }

    public Optional<Visitor> findById(Long id) {
        return visitors.stream().filter(v -> v.getId().equals(id)).findFirst();
    }
}
