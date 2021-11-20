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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService,
                          UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/users/inject")
    public String injectData() {
        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Smile");
        User alice = new User();
        alice.setName("Alice");
        alice.setLastName("Frank");
        User john = new User();
        john.setName("John");
        john.setLastName("Smith");

        userService.add(bob);
        userService.add(alice);
        userService.add(john);

        return "Done!";
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        if (userService.get(userId) != null) {
            return userDtoMapper.parse(userService.get(userId));
        }
        return null;
    }

    @GetMapping("/users/")
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
