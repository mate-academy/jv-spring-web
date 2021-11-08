package mate.academy.spring.service.mapper;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }

    public User toModel(UserResponseDto userResponseDto) {
        User user = new User();
        user.setName(userResponseDto.getName());
        user.setLastName(userResponseDto.getLastName());
        user.setId(userResponseDto.getId());
        return user;
    }

}
