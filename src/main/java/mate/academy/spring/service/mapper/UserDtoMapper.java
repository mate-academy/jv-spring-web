package mate.academy.spring.service.mapper;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserResponseDto parseToDto(User user) {
        UserResponseDto userResponseDto =
                new UserResponseDto(user.getId(), user.getName(), user.getLastName());
        return userResponseDto;
    }
}
