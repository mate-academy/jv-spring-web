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

    @GetMapping("/inject")
    public String injectTestData() {
        User userBob = new User();
        userBob.setName("Bob");
        userBob.setLastName("Smith");
        userService.add(userBob);

        User userAlice = new User();
        userAlice.setName("Alice");
        userAlice.setLastName("Jackson");
        userService.add(userAlice);

        User userKate = new User();
        userKate.setName("Kate");
        userKate.setLastName("Li");
        userService.add(userKate);
        return "Done!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(user -> userMapper.parse(user))
                .collect(Collectors.toList());
    }
}
