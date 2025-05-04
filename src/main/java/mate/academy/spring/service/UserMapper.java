package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;

public class UserMapper {
    public static UserResponseDto parseToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        return responseDto;
    }
}
