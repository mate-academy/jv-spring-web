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

    public UserController(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String injectUsers() {
        userService.add(new User("John", "Doe"));
        userService.add(new User("Emily", "Stone"));
        userService.add(new User("Hugh", "Dane"));
        return "Users are injected!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.toUserResponseDto(userService.get(id));
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
            .map(userMapper::toUserResponseDto)
            .collect(Collectors.toList());
    }
}
