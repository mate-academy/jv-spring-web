package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper
            userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userDtoMapper::parse).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public String usersInjectMockData() {
        User u1 = new User();
        u1.setFirstName("John");
        u1.setLastName("Doe");
        User u2 = new User();
        u2.setFirstName("Emily");
        u2.setLastName("Stone");
        User u3 = new User();
        u3.setFirstName("Hugh");
        u3.setLastName("Dane");
        userService.add(u1);
        userService.add(u2);
        userService.add(u3);
        return "Users are injected!";
    }
}
