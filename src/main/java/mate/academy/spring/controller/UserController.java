package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
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

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parseToDto(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User bob = new User("Alice", "Alison");
        User alice = new User("Bob", "Bobov");
        User john = new User("John", "Wick");
        userService.add(alice);
        userService.add(bob);
        userService.add(john);
        return "Mock data inject - done.";
    }
}
