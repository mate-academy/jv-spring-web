package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
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
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.listUsers().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User ilia = new User();
        ilia.setName("Ilia");
        ilia.setLastName("Sutnik");

        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Pivovar");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Sarancha");

        User lena = new User();
        lena.setName("Elena");
        lena.setLastName("Bilyk");

        userService.add(ilia);
        userService.add(bob);
        userService.add(alice);
        userService.add(lena);
        return "Injected data to DB!";
    }
}
