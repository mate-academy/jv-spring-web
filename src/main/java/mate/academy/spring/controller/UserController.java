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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        User user2 = new User();
        user2.setFirstName("Emily");
        user2.setLastName("Stone");
        User user3 = new User();
        user3.setFirstName("Hugh");
        user3.setLastName("Dane");
        userService.add(user);
        userService.add(user2);
        userService.add(user3);
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping
    List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
