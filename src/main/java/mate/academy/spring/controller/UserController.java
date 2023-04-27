package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.mapper.UserDtoMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping()
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userDtoMapper::parse).toList();
    }

    @GetMapping("/inject")
    public String injectMockData() {
        List<User> users = List.of(
                new User("John", "Doe"),
                new User("Emily", "Stone"),
                new User("Hugh", "Dane"));
        users.forEach(userService::add);
        return "Users are injected!";
    }
}
