package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
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
    public String inject() {
        User bob = new User();
        bob.setFirstName("John");
        bob.setLastName("Doe");
        userService.add(bob);
        User alice = new User();
        alice.setFirstName("Emily");
        alice.setLastName("Stone");
        userService.add(alice);
        User yaroslav = new User();
        yaroslav.setFirstName("Hugh");
        yaroslav.setLastName("Dane");
        userService.add(yaroslav);
        return "Users are injected!";
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseToDto(userService.get(userId));
    }
}
