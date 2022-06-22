package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;

    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public static UserResponseDto getDtoFromUser(User user) {
        return new UserResponseDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    public User getUserFromDto(UserResponseDto userRd) {
        return new User(userRd.getFirstName(), userRd.getLastName());
    }
}
