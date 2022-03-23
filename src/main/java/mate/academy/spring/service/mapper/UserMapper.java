package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    public User toUser(UserResponseDto userResponseDto) {
        return new User(userResponseDto.getFirstName(), userResponseDto.getLastName());
    }
}
