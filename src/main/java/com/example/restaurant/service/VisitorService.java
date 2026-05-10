package com.example.restaurant.service;

import com.example.restaurant.dto.user.UserRequestDto;
import com.example.restaurant.dto.user.UserResponseDto;
import com.example.restaurant.model.Visitor;
import com.example.restaurant.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) { this.visitorRepository = visitorRepository; }

    public UserResponseDto create(UserRequestDto dto) {
        validate(dto);
        return toDto(visitorRepository.save(new Visitor(null, dto.name(), dto.age(), dto.gender())));
    }

    public UserResponseDto update(Long id, UserRequestDto dto) {
        validate(dto);
        visitorRepository.findById(id).orElseThrow();
        return toDto(visitorRepository.save(new Visitor(id, dto.name(), dto.age(), dto.gender())));
    }

    public void remove(Long id) { visitorRepository.deleteById(id); }
    public List<UserResponseDto> findAll() { return visitorRepository.findAll().stream().map(this::toDto).toList(); }
    public UserResponseDto findById(Long id) { return toDto(visitorRepository.findById(id).orElseThrow()); }

    private void validate(UserRequestDto dto) { if (dto.age() == null || dto.gender() == null) throw new IllegalArgumentException("age и gender обязательны"); }
    private UserResponseDto toDto(Visitor v) { return new UserResponseDto(v.getId(), v.getName(), v.getAge(), v.getGender()); }
}
