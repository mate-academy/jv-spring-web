package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
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
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public void injectMockData() {
        User ben = new User();
        ben.setName("Benito");
        ben.setLastName("Zara");
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Biffaro");
        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Gallo");
        userService.add(ben);
        userService.add(bob);
        userService.add(alice);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return UserMapper.parse(userService.get(userId));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(UserMapper::parse)
                .collect(Collectors.toList());
    }
}
