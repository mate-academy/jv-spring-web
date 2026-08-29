package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;

public interface UserController {

    public String inject();

    public UserResponseDto get(Long userId);

    public List<UserResponseDto> getAll();
}
