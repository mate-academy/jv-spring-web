package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserDtoMapper userDtoMapper, UserService userService) {
        this.userDtoMapper = userDtoMapper;
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/inject")
    public String inject() {
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
