package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
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

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(userMapper::parseIntoUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseIntoUserDto(userService.getById(userId));
    }

    @GetMapping("/inject")
    public String inject() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Antonov");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Antonova");

        User john = new User();
        john.setName("John");
        john.setLastName("Dou");

        userService.add(bob);
        userService.add(alice);
        userService.add(john);

        return "Done!";
    }
}
