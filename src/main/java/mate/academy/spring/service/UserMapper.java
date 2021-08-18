package mate.academy.spring.service;

import mate.academy.spring.dao.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getLastName());
    }
}
