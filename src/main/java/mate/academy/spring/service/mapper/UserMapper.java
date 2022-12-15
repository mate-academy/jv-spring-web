package mate.academy.spring.service.mapper;

import org.springframework.stereotype.Component;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userDto = new UserResponseDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
