package mate.academy.spring.service;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public User mapToObject(UserResponseDto userResponseDto) {
        return modelMapper.map(userResponseDto, User.class);
    }

    public UserResponseDto mapToDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

}
