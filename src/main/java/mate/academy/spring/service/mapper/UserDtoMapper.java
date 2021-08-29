package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getLastName());
    }
}
