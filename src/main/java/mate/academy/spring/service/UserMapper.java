package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        return responseDto;
    }

    public User toModel(UserResponseDto responseDto) {
        User user = new User();
        user.setFirstName(responseDto.getFirstName());
        user.setLastName(responseDto.getLastName());
        return user;
    }
}
