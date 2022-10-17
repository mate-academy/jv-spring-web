package mate.academy.spring.model;

import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {
    public UserResponseDto parse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }
}
