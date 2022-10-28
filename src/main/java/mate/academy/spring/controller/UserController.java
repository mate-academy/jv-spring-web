package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserDtoMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.toDto(userService.get(userId));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/inject")
    public String inject() {
        User johnDoe = new User();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");

        User emilyStone = new User();
        emilyStone.setFirstName("Emily");
        emilyStone.setLastName("Stone");

        User hughDane = new User();
        hughDane.setFirstName("Hugh");
        hughDane.setLastName("Dane");

        userService.add(johnDoe);
        userService.add(emilyStone);
        userService.add(hughDane);

        return "Users are injected!";
    }
}
