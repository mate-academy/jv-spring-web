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

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public String inject() {
        User firstUser = new User();
        firstUser.setFirstName("John");
        firstUser.setLastName("Doe");
        userService.add(firstUser);

        User secondUser = new User();
        secondUser.setFirstName("Emily");
        secondUser.setLastName("Stone");
        userService.add(secondUser);

        User thirdUser = new User();
        thirdUser.setFirstName("Hugh");
        thirdUser.setLastName("Dane");
        userService.add(thirdUser);

        return "Users are injected!";
    }
}
