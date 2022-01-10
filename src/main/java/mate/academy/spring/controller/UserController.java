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

    @GetMapping("/inject")
    public String injectUsers() {
        User user1 = new User();
        user1.setName("name1");
        user1.setLastName("lastName1");
        userService.add(user1);
        User user2 = new User();
        user2.setName("name2");
        user2.setLastName("lastName2");
        userService.add(user2);
        User user3 = new User();
        user3.setName("name3");
        user3.setLastName("lastName3");
        userService.add(user3);
        return "Injected 3 users.";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.mapUserToDto(userService.get(userId));
    }

    @GetMapping
    List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::mapUserToDto)
                .collect(Collectors.toList());
    }
}
