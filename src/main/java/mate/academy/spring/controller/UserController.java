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
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockUsers() {
        User johnDoe = new User();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");

        User emilyStone = new User();
        emilyStone.setFirstName("Emily");
        emilyStone.setLastName("Stone");

        User hughDane = new User();
        hughDane.setFirstName("Hugh");
        hughDane.setLastName("Dane");

        userService.add(johnDoe);
        userService.add(emilyStone);
        userService.add(hughDane);
        return "Users are injected!";
    }
}
