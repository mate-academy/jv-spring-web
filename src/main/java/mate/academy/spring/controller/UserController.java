package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        User userBob = new User();
        userBob.setLastName("Chupika");
        userBob.setName("Bobby");

        User userAlice = new User();
        userAlice.setLastName("Chupika");
        userAlice.setName("Alice");

        User userJohn = new User();
        userJohn.setLastName("Chupika");
        userJohn.setName("John");

        userService.add(userBob);
        userService.add(userAlice);
        userService.add(userJohn);

        return "Done";
    }

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseIntoUserDto(userService.getById(userId));
    }

    @GetMapping
    List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userMapper::parseIntoUserDto)
                .collect(Collectors.toList());
    }
}
