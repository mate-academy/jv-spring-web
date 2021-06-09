package mate.academy.spring.service;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;

public interface UserMapper {
    UserResponseDto parseIntoUserDto(User user);

    User parseIntoUserModel(UserResponseDto userResponseDto);
}
