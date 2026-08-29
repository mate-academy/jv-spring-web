package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping("/users/inject")
    public String inject() {
        injectUsers();
        return "Users are injected!";
    }

    private void injectUsers() {
        User johnUser = new User();
        johnUser.setFirstName("John");
        johnUser.setLastName("Doe");

        User emilyUser = new User();
        emilyUser.setFirstName("Emily");
        emilyUser.setLastName("Stone");

        User hughUser = new User();
        hughUser.setFirstName("Hugh");
        hughUser.setLastName("Dane");

        userService.add(johnUser);
        userService.add(emilyUser);
        userService.add(hughUser);
    }
}
