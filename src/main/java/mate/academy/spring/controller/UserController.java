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

    @GetMapping("/inject")
    public String injectMockData() {
        User bob = new User();
        bob.setName("bob");
        bob.setLastName("smith");
        User alice = new User();
        alice.setName("alice");
        alice.setLastName("poderevianska");
        User john = new User();
        john.setName("john");
        john.setLastName("li");
        User kirk = new User();
        kirk.setName("kirk");
        kirk.setLastName("zimmerman");
        userService.add(bob);
        userService.add(alice);
        userService.add(kirk);
        userService.add(john);
        return "Done";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
