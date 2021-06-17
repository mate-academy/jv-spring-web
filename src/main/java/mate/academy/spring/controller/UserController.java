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
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping()
    public List<UserResponseDto> getAll() {
        return userService.listUsers()
               .stream().map(userDtoMapper::parse)
               .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Weak");

        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Vovk");

        User ira = new User();
        ira.setName("Ira");
        ira.setLastName("Ust");

        userService.add(bob);
        userService.add(alice);
        userService.add(ira);
        return "Done";
    }
}
