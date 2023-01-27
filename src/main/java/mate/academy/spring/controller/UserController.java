package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.maper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserMapper userMapper;
    private UserService userService;

    UserController(@Autowired UserMapper userMapper,
                   @Autowired UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.toDto(userService.getById(id));
    }

    @GetMapping("")
    List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        userService.add(new User("John","Doe"));
        userService.add(new User("Emily","Stone"));
        userService.add(new User("Hugh","Dane"));
        return "Users are injected!";
    }
}
