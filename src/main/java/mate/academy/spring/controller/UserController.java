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

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parseToDto(userService.getById(id));
    }

    @GetMapping("/inject")
    public String injectUsers() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Thompson");
        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Parker");
        User nazar = new User();
        nazar.setName("Nazar");
        nazar.setLastName("Bilyak");

        userService.add(bob);
        userService.add(alice);
        userService.add(nazar);

        return "Done!";
    }
}
