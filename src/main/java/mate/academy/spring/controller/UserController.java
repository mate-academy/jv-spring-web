package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        UserResponseDto userDto1 = new UserResponseDto();
        userDto1.setFirstName("John");
        userDto1.setLastName("Doe");
        userService.add(userMapper.toModel(userDto1));
        UserResponseDto userDto2 = new UserResponseDto();
        userDto2.setFirstName("Emily");
        userDto2.setLastName("Stone");
        userService.add(userMapper.toModel(userDto2));
        UserResponseDto userDto3 = new UserResponseDto();
        userDto3.setFirstName("Hugh");
        userDto3.setLastName("Dane");
        userService.add(userMapper.toModel(userDto3));
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping("/")
    List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
