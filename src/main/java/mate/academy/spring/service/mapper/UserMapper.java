package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;

public class UserMapper {
    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(user.getName(), user.getLastName());
    }

    public User toUser(UserResponseDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
