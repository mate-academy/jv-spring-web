package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
        return userService.listUsers().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public RedirectView injectTestUsers() {
        User user = new User();
        user.setName("Ivan");
        user.setLastName("Ivanov");

        User user1 = new User();
        user1.setName("Petr");
        user1.setLastName("Petrov");

        userService.add(user);
        userService.add(user1);
        return new RedirectView("/users");
    }
}
