package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;

public interface UserController {
    String injectMockData();

    UserResponseDto get(Long userId);

    List<UserResponseDto> getAll();
}
