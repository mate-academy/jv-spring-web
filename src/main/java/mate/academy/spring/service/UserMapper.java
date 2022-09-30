package mate.academy.spring.service;

import org.springframework.stereotype.Component;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }
}
