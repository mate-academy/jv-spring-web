package mate.academy.spring.service;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserResponseDto parse(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getLastName());
    }

}
