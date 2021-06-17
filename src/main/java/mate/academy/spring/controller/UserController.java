package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        User jack = new User();
        jack.setName("Jack");
        jack.setLastName("Lolli");

        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Hatson");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Bib");

        userService.add(jack);
        userService.add(bob);
        userService.add(alice);

        return "Users created!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAllUser() {
        return userService.listUsers()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
