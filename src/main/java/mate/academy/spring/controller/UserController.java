package mate.academy.spring.controller;

import mate.academy.spring.model.dto.UserResponseDto;

import java.util.List;

public interface UserController {
    UserResponseDto get(Long userId);
    List<UserResponseDto> getAll();
}
