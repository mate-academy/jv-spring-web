package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.dto.mapper.UserMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
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
        List<User> users = List.of(
                new User("John", "Doe"),
                new User("Emily", "Stone"),
                new User("Hugh", "Dane")
        );
        users.forEach(userService::add);
        return "Users are injected!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.mapToResponse(userService.get(id));
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::mapToResponse)
                .collect(Collectors.toList());
    }
}
