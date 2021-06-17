package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setId(user.getId());
        response.setLastName(user.getLastName());
        response.setName(user.getName());
        return response;
    }
}
