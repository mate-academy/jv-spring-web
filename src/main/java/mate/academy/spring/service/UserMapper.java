package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponseDto mapFromEntityToDto(User user) {
        return new UserResponseDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }
}
