package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setName(user.getName());
        return userResponseDto;
    }
}
