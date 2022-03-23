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

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectUsers() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Ray");
        userService.add(bob);

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Bar");
        userService.add(alice);

        User nick = new User();
        nick.setName("Nick");
        nick.setLastName("Jr.");
        userService.add(nick);

        User amanda = new User();
        amanda.setName("Amanda");
        amanda.setLastName("Seyfried");
        userService.add(amanda);

        return "Done!";
    }
}
