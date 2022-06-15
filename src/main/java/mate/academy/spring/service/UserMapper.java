package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userDto = new UserResponseDto(user.getFirstName(), user.getLastName());
        userDto.setId(user.getId());
        return userDto;
    }
}
