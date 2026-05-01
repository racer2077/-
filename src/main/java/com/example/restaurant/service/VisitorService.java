package com.example.restaurant.service;

import com.example.restaurant.dto.user.UserRequestDto;
import com.example.restaurant.dto.user.UserResponseDto;
import com.example.restaurant.model.Visitor;
import com.example.restaurant.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;
    private final AtomicLong idGenerator = new AtomicLong(0);

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public UserResponseDto create(UserRequestDto dto) {
        validate(dto);
        Long id = idGenerator.incrementAndGet();
        Visitor visitor = new Visitor(id, dto.name(), dto.age(), dto.gender());
        visitorRepository.save(visitor);
        return toDto(visitor);
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        validate(dto);
        visitorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Посетитель не найден"));
        Visitor visitor = new Visitor(id, dto.name(), dto.age(), dto.gender());
        visitorRepository.save(visitor);
        return toDto(visitor);
    }

    public void remove(Long id) { visitorRepository.remove(id); }

    public List<UserResponseDto> findAll() { return visitorRepository.findAll().stream().map(this::toDto).toList(); }

    public UserResponseDto findById(Long id) { return toDto(visitorRepository.findById(id).orElseThrow()); }

    private void validate(UserRequestDto dto) {
        if (dto.age() == null || dto.gender() == null) throw new IllegalArgumentException("age и gender обязательны");
    }

    private UserResponseDto toDto(Visitor v) { return new UserResponseDto(v.getId(), v.getName(), v.getAge(), v.getGender()); }
}
