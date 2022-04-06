package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserDtoMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable String userId) {
        return userDtoMapper.parse(userService.get(Long.parseLong(userId)));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/inject")
    public String inject() {
        List.of(
                new User("John", "Doe"),
                new User("Emily", "Stone"),
                new User("Hugh", "Dane"))
                .forEach(userService::add);
        return "Users are injected!";
    }

}
