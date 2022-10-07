package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/users/inject")
    public String userAdd() {
        User userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setFirstName("Doe");

        User userEmily = new User();
        userEmily.setFirstName("Emily");
        userEmily.setFirstName("Stone");

        User userHugh = new User();
        userHugh.setFirstName("Hugh");
        userHugh.setFirstName("Dane");

        userService.add(userJohn);
        userService.add(userEmily);
        userService.add(userHugh);

        return "Users are injected!";
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

}
