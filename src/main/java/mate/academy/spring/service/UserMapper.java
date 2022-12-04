package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto parseToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setLastName(user.getLastName());
        responseDto.setFirstName(user.getFirstName());
        return responseDto;
    }

    public User parseFromDto(UserResponseDto responseDto) {
        User user = new User();
        user.setLastName(responseDto.getLastName());
        user.setFirstName(responseDto.getFirstName());
        user.setId(responseDto.getId());
        return user;
    }
}
