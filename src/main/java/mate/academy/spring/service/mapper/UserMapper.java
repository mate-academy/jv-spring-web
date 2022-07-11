package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserRequestDto;
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

    public User toModel(UserRequestDto requestDto) {
        User user = new User();
        user.setLastName(requestDto.getLastName());
        user.setFirstName(requestDto.getFirstName());
        return user;
    }
}
