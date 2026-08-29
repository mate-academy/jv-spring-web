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

    public UserController(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User johnUser = new User();
        johnUser.setFirstName("John");
        johnUser.setLastName("Doe");

        User emilyUser = new User();
        emilyUser.setFirstName("Emily");
        emilyUser.setLastName("Stone");

        User hughUser = new User();
        hughUser.setFirstName("Hugh");
        hughUser.setLastName("Dane");

        userService.add(johnUser);
        userService.add(emilyUser);
        userService.add(hughUser);
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll()
                .stream().map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
