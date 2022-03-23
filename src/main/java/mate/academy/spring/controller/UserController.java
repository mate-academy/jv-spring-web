package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
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

    @GetMapping("/")
    List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userMapper::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable String userId) {
        return userMapper.toUserResponseDto(userService.get(Long.parseLong(userId)));
    }

    @GetMapping("/inject")
    public String injectMockUsers() {
        User bob = new User("Bob", "Dou");
        User alice = new User("Alice", "Bou");
        User john = new User("John", "Lee");
        User sviat = new User("Sviatoslav", "Hrynyk");
        User obama = new User("Barack", "Obama");

        userService.add(bob);
        userService.add(alice);
        userService.add(john);
        userService.add(sviat);
        userService.add(obama);
        return "Users were added!";
    }
}
