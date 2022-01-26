package mate.academy.spring.service.mapper;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserDtoMapper() {
    }

    public UserResponseDto parse(User user) {
        UserResponseDto current = new UserResponseDto();
        current.setId(user.getId());
        current.setName(user.getName());
        current.setLastName(user.getLastName());
        return current;
    }
}
