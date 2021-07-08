package mate.academy.spring.service;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;

public class UserMapper {

    private UserMapper() {}

    public static UserResponseDto parse(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getLastName());
    }

}
