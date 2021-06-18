package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseToDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectUsers() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Kennedy");
        userService.add(bob);

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Wonderland");
        userService.add(alice);

        User john = new User();
        john.setName("John");
        john.setLastName("Kennedy");
        userService.add(john);

        return "Done!";
    }
}
