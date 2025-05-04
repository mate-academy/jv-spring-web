package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String getInject() {
        List<User> demoUsers = List.of(new User("John", "Doe"),
                new User("Emily", "Stone"), new User("Hugh", "Dane"));
        demoUsers.forEach(userService::add);
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return Stream.of(userService.get(userId))
                .map(userMapper::mapUserToDto)
                .findFirst()
                .orElseThrow();
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::mapUserToDto)
                .collect(Collectors.toList());
    }
}
