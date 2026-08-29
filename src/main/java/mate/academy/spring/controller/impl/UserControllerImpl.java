package mate.academy.spring.controller.impl;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.controller.UserController;
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
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserMapper userDtoMapper;

    public UserControllerImpl(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    @GetMapping("/inject")
    public String inject() {
        User user1 = new User("John", "Doe");
        User user2 = new User("Emily", "Stone");
        User user3 = new User("Hugh", "Dane");
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        return "Users are injected!";
    }

    @Override
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @Override
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
