package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
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

    @GetMapping("/inject")
    public String inject() {
        User bob = new User("John", "Doe");
        User emily = new User("Emily", "Stone");
        User hugh = new User("Hugh", "Dane");
        userService.add(bob);
        userService.add(emily);
        userService.add(hugh);
        return "Users are injected!";
    }

    @GetMapping("")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .toList();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        User user = userService.get(userId);
        return userMapper.parse(user);
    }
}
