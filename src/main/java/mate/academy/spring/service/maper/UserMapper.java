package mate.academy.spring.service.maper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;

public interface UserMapper {
    UserResponseDto toDto(User userResponseDto);
}
