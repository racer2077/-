package com.example.restaurant.service;

import com.example.restaurant.model.Visitor;
import com.example.restaurant.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public void save(Visitor visitor) {
        if (visitor.getId() == null || visitor.getAge() == null || visitor.getGender() == null) {
            throw new IllegalArgumentException("Поля id, age и gender обязательны");
        }
        visitorRepository.save(visitor);
    }

    public void remove(Visitor visitor) {
        visitorRepository.remove(visitor);
    }

    public List<Visitor> findAll() {
        return visitorRepository.findAll();
    }
}
