package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping
    List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Alison");
        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Bobson");
        User john = new User();
        john.setName("John");
        john.setLastName("Bobalison");
        userService.add(bob);
        userService.add(alice);
        userService.add(john);
        return "Injection was success.";
    }
}
