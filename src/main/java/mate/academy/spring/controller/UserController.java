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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ResponseBody()
    @GetMapping("/users/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @ResponseBody()
    @GetMapping("/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/users/inject")
    public String inject() {
        User johnDoeUser = new User("John", "Doe");
        User emilyStoneUser = new User("Emily", "Stone");
        User hughDaneUser = new User("Hugh", "Dane");
        userService.add(johnDoeUser);
        userService.add(emilyStoneUser);
        userService.add(hughDaneUser);
        return "Users are injected!";
    }
}
