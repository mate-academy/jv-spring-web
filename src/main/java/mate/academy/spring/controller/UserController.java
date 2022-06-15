package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
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

    @GetMapping("/users/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
            .map(userMapper::parse)
            .collect(Collectors.toList());
    }

    @GetMapping("/users/inject")
    public String injectMockData() {
        User firstUser = new User("John", "Doe");
        User secondUser = new User("Emily", "Stone");
        User thirdUser = new User("Hugh", "Dane");

        userService.add(firstUser);
        userService.add(secondUser);
        userService.add(thirdUser);

        return "Users are injected!";
    }
}
