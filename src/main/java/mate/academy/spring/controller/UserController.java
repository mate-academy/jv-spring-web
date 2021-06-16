package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService,
                          UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @ResponseBody
    @GetMapping("/inject")
    public String create() {
        User bob = new User("Bob", "R");
        User alice = new User("Alica", "Tron");
        User kris = new User("Kris", "Evans");
        userService.add(bob);
        userService.add(alice);
        userService.add(kris);
        return "Done";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.getById(userId));
    }

    @ResponseBody
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}

