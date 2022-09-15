package mate.academy.spring.controller;

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

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        userService.add(user);
        user.setFirstName("Emily");
        user.setLastName("Stone");
        userService.add(user);
        user.setFirstName("Hugh");
        user.setLastName("Dane");
        userService.add(user);
        return "Users are injected!";
    }

    @GetMapping("/{id}")
    public UserResponseDto getByIndex(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }
}

