package mate.academy.spring.service.mapper;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;

public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setCountry(userResponseDto.getCountry());
        return userResponseDto;
    }
}
