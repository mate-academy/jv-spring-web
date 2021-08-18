package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String add() {
        User test1 = new User("test1", "1");
        User test2 = new User("test2", "2");
        User test3 = new User("test3", "3");
        User test4 = new User("test4", "4");
        userService.add(test1);
        userService.add(test2);
        userService.add(test3);
        userService.add(test4);
        return "Done!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
