package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public UserResponseDto parse(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setId(user.getId());
        return responseDto;
    }

    public User toModel(UserResponseDto userResponseDto) {
        User user = new User();
        user.setFirstName(userResponseDto.getFirstName());
        user.setLastName(userResponseDto.getLastName());
        return user;
    }
}
