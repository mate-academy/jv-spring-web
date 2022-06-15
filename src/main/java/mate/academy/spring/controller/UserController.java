package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
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

    @GetMapping("/inject")
    public String injectMockUsers(User user) {
        User userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userService.add(userJohn);
        User userEmily = new User();
        userEmily.setFirstName("Emily");
        userEmily.setLastName("Stone");
        userService.add(userEmily);
        User userHugh = new User();
        userHugh.setFirstName("Hugh");
        userHugh.setLastName("Dane");
        userService.add(userHugh);
        return "Users are injected!";
    }

    @GetMapping()
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }
}
