package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String injectUsers() {
        userService.add(new User("John", "Doe"));
        userService.add(new User("Emily", "Stone"));
        userService.add(new User("Hugh", "Dane"));
        return "Users are injected!";
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getAll().stream().map(userDtoMapper::parse).toList();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

}
