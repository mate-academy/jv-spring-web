package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parseToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userResponseDto.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }
}
