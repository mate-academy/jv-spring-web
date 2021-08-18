package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
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

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String createMockUsers() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Kovalski");
        userService.add(bob);
        User alice = new User();
        bob.setName("Alice");
        bob.setLastName("Smith");
        userService.add(alice);
        User travis = new User();
        travis.setName("Travis");
        travis.setLastName("Kalafior");
        userService.add(travis);
        return "created";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }
}
