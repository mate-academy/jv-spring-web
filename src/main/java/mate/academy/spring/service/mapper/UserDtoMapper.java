package mate.academy.spring.service.mapper;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserRequestDto;
import mate.academy.spring.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public static UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }

    public static User toModel(UserRequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        return user;
    }
}
