package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public DemoController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/users/inject")
    public String injectMockData() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");

        User emily = new User();
        emily.setFirstName("Emily");
        emily.setLastName("Stone");

        User hugh = new User();
        hugh.setFirstName("Hugh");
        hugh.setLastName("Dane");

        userService.add(john);
        userService.add(emily);
        userService.add(hugh);

        return "Users are injected!";
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }
}
