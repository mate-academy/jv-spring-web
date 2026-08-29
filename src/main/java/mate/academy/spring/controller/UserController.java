package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/inject")
    public String get() {
        User doe = new User();
        doe.setFirstName("John");
        doe.setLastName("Doe");

        User stone = new User();
        stone.setFirstName("Emily");
        stone.setLastName("Stone");

        User dane = new User();
        dane.setFirstName("Hugh");
        dane.setLastName("Dane");

        userService.add(doe);
        userService.add(stone);
        userService.add(dane);

        return "Users are injected!";
    }

    @GetMapping("users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll()
               .stream()
               .map(userMapper::parse)
               .collect(Collectors.toList());
    }
}
