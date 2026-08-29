package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("")
    private List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse).toList();
    }

    @GetMapping("/{id}")
    private UserResponseDto getById(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    private String usersInjectMockData() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        userService.add(user1);
        User user2 = new User();
        user2.setFirstName("Emily");
        user2.setLastName("Stone");
        userService.add(user2);
        User user3 = new User();
        user3.setFirstName("Hugh");
        user3.setLastName("Dane");
        userService.add(user3);
        return "Users are injected!";
    }
}
