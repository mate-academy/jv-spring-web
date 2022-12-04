package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ApplicationContext applicationContext;

    public UserMapper(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public UserResponseDto toUserResponseDto(User user) {
        UserResponseDto userResponseDto = applicationContext.getBean(UserResponseDto.class);
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        return userResponseDto;
    }
}
