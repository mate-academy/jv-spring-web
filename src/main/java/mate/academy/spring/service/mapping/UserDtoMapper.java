package mate.academy.spring.service.mapping;

import mate.academy.spring.dto.UserRequestDto;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

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
        user.setId(requestDto.getId());
        return user;
    }
}
