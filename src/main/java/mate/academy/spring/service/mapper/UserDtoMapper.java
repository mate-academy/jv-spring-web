package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResopnseDto = new UserResponseDto();
        userResopnseDto.setId(user.getId());
        userResopnseDto.setFirstName(user.getFirstName());
        userResopnseDto.setLastName(user.getLastName());
        return userResopnseDto;
    }
}
