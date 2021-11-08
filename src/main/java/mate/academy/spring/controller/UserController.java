package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(Long userId) {
        return UserDtoMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(UserDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockUsers() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Bobchenko");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Alison");

        User max = new User();
        max.setName("Max");
        max.setLastName("Maxenko");

        userService.add(bob);
        userService.add(alice);
        userService.add(max);
        return "Mock users have been created";
    }
}
