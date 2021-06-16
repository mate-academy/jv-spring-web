package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.toDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectUsers() {
        User nick = new User();
        nick.setName("Nick");
        nick.setLastName("Manko");
        userService.add(nick);

        User bill = new User();
        bill.setName("Bill");
        bill.setLastName("Golden");
        userService.add(bill);

        User john = new User();
        john.setName("John");
        john.setLastName("Voight");
        userService.add(john);

        return "Initialized";
    }
}
