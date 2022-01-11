package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
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

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping("/users/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/inject")
    public void injectMockData() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Smith");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Peach");

        User tom = new User();
        tom.setName("Tom");
        tom.setLastName("Sawyer");

        User huckleberry = new User();
        huckleberry.setName("Huckleberry");
        huckleberry.setLastName("Finn");

        userService.add(bob);
        userService.add(alice);
        userService.add(tom);
        userService.add(huckleberry);
    }
}
