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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/inject")
    public String injectUsers() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        userService.add(user);

        User user1 = new User();
        user1.setFirstName("Emily");
        user1.setLastName("Stone");
        userService.add(user1);

        User user2 = new User();
        user2.setFirstName("Hugh");
        user2.setLastName("Dane");
        userService.add(user2);
        return "Users are injected!";
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseUser(userService.get(userId));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parseUser)
                .collect(Collectors.toList());
    }
}
