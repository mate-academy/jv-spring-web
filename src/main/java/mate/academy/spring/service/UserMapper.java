package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserResponseDto toDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }
}
