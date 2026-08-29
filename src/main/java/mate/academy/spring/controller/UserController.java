package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/inject")
    public String injectData() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setFirstName("Emily");
        user2.setLastName("Stone");

        User user3 = new User();
        user3.setFirstName("Hugh");
        user3.setLastName("Dane");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        return "Users are injected!";
    }

    @GetMapping("/users/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
