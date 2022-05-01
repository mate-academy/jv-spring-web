package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserMapper userMapper;
    private UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping("")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        userService.add(userMapper.convert(new UserResponseDto("Jhon", "Doe")));
        userService.add(userMapper.convert(new UserResponseDto("Emily", "Stone")));
        userService.add(userMapper.convert(new UserResponseDto("Hugh", "Dane")));
        return "Users are injected!";
    }
}
