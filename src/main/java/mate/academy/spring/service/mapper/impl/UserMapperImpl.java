package mate.academy.spring.service.mapper.impl;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponseDto parse(User user) {
        return new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName());
    }
}
