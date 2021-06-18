package mate.academy.spring.service.mapper;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponceDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponceDto parseToDto(User user) {
        UserResponceDto userResponceDto = new UserResponceDto();
        userResponceDto.setId(user.getId());
        userResponceDto.setName(user.getName());
        userResponceDto.setLastName(user.getLastName());
        return userResponceDto;
    }
}
