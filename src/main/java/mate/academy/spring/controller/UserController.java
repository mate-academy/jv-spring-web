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
        return userService.getAll().stream().map(userMapper::parse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User bob = new User();
        bob.setFirstName("Bob");
        bob.setLastName("Bean");

        User alice = new User();
        alice.setFirstName("Alice");
        alice.setLastName("Carroll");

        User jimmy = new User();
        jimmy.setFirstName("Jimmy");
        jimmy.setLastName("Carter");

        userService.add(bob);
        userService.add(alice);
        userService.add(jimmy);

        return "done!";
    }
}
