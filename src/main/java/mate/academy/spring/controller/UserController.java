package mate.academy.spring.controller;

import java.util.Arrays;
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

@RestController()
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/")
    public List<UserResponseDto> getUsers() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User[] mySiblings = {
                new User("Andrew", "Kuruch"),
                new User("Tanya", "Kasap"),
                new User("Irina", "Gordt")
        };
        Arrays.stream(mySiblings).forEach(userService::add);
        return "Test users have been saved";
    }
}
