package mate.academy.spring.service.mapper;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    private UserService userService;

    public UserResponseDto get(Long userId) {
        User user = userService.get(userId);
        UserResponseDto dtoUser = new UserResponseDto();
        dtoUser.setId(user.getId());
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        return dtoUser;
    }

    public UserResponseDto parse(User user) {
        UserResponseDto dtoUser = new UserResponseDto();
        dtoUser.setId(user.getId());
        dtoUser.setFirstName(user.getFirstName());
        dtoUser.setLastName(user.getLastName());
        return dtoUser;
    }
}
