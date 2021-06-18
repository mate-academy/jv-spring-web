package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponceDto;
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
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserResponceDto get(@PathVariable Long userId) {
        return userMapper.parseToDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponceDto> getAll() {
        return userService.listUsers()
                .stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Bobson");
        userService.add(alice);

        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Alicon");
        userService.add(bob);

        User ivan = new User();
        ivan.setName("Ivan");
        ivan.setLastName("Petrov");
        userService.add(ivan);

        return "Users injected.";
    }
}
