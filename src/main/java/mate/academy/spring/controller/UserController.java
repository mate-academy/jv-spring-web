package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.model.dto.UserResponseDto;

public interface UserController {
    UserResponseDto get(Long userId);

    List<UserResponseDto> getAll();
}
