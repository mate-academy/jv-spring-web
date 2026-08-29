package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto urd = new UserResponseDto();
        urd.setId(user.getId());
        urd.setFirstName(user.getFirstName());
        urd.setLastName(user.getLastName());
        return urd;
    }
}
