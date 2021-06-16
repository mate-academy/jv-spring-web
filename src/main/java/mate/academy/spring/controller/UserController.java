package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.dto.UserResponseDto;
import mate.academy.spring.service.mappers.UserMapper;
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

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parseEntityToDto(userService.get(id));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(userMapper::parseEntityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("SquarePants");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Jackson");

        User jack = new User();
        jack.setName("Jack");
        jack.setLastName("Ivanov");

        userService.add(bob);
        userService.add(alice);
        userService.add(jack);
        return "Done!";
    }
}
