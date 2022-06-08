package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setId(user.getId());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }

    public User toModel(UserResponseDto userResponseDto) {
        User user = new User();
        user.setId(userResponseDto.getId());
        user.setFirstName(userResponseDto.getFirstName());
        user.setLastName(user.getLastName());
        return user;
    }
}
