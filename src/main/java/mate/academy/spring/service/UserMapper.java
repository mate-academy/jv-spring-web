package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapUserToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto(user.getFirstName(),
                user.getLastName());
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
}
