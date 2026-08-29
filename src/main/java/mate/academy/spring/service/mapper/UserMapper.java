package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserRequestDto;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto parse(User user) {
        UserResponseDto responsDto = new UserResponseDto();
        responsDto.setId(user.getId());
        responsDto.setFirstName(user.getFirstName());
        responsDto.setLastName(user.getLastName());
        return responsDto;
    }

    public User toUser(UserRequestDto requestDto) {
        User user = new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        return user;
    }

}
