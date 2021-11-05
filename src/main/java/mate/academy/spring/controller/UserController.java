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
    public void injectMockData() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Tramp");
        User jim = new User();
        jim.setName("Jim");
        jim.setLastName("Kerry");
        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Kennedy");
        userService.add(bob);
        userService.add(jim);
        userService.add(alice);
    }

    @GetMapping("/")
    List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userDtoMapper::parse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }
}
