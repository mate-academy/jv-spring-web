package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;

public interface UserMapper {
    UserResponseDto parse(User user);
}
