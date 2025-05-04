package mate.academy.spring.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setFirstName(user.getFirstName());
        return userResponseDto;
    }
}
