package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserRequestDto;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto parse(User user) { //parse=model to DTO
        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());

        return responseDto;
    }

    public User toModel(UserRequestDto requestDto) {
        User user = new User();

        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());

        return user;
    }
}
