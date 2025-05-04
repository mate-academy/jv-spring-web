package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUserFromDto(UserResponseDto userResponseDto) {
        User user = new User(userResponseDto.getFirstName(), userResponseDto.getLastName());
        if (userResponseDto.getId() != null) {
            user.setId(userResponseDto.getId());
        }
        return user;
    }

    public UserResponseDto mapToDtoFromUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto(user.getFirstName(),
                user.getLastName());
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
}
