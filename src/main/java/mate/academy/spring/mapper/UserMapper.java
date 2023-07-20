package mate.academy.spring.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapToUserDtoFromUser(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public User mapToUserFromUserDto(UserResponseDto userResponseDto) {
        User user = new User(userResponseDto.getFirstName(), userResponseDto.getLastName());
        user.setId(userResponseDto.getId());
        return user;
    }
}
