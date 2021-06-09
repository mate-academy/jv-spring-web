package mate.academy.spring.service;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponseDto parseIntoUserDto(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getLastName());
    }
}
