package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
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

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse).collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");

        User kyle = new User();
        kyle.setFirstName("Emily");
        kyle.setLastName("Stone");

        User aleks = new User();
        aleks.setFirstName("Hugh");
        aleks.setLastName("Dane");

        userService.add(john);
        userService.add(kyle);
        userService.add(aleks);
        return "Users are injected!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }
}
