package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto responseDto = new UserResponseDto(user.getId(),
                user.getFirstName(), user.getLastName());
        return responseDto;
    }

    public User toModel(UserResponseDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName((requestDto.getLastName()));
        return user;
    }
}
