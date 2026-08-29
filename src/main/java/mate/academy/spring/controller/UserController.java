package mate.academy.spring.controller;

import mate.academy.spring.model.User;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.service.ObjectMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private ObjectMapper userMapper;

    public UserController(UserService userService, ObjectMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(Long userId) {
        return userMapper
                .userResponseDto(userService.get(userId));
    }

    @GetMapping("/All")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userMapper::userResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/usersInject")
    public String injectUsers() {
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
}
