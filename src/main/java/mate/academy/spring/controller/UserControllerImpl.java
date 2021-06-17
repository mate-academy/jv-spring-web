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
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserControllerImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    @GetMapping("/inject")
    public String injectUsers() {
        User bob = new User("Bob", "Smith");
        User alice = new User("Alice", "Valentine");
        User john = new User("John", "Markus");

        userService.add(bob);
        userService.add(alice);
        userService.add(john);
        return "Users successfully added to DB";
    }

    @Override
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseToDto(userService.get(userId));
    }

    @Override
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }
}
