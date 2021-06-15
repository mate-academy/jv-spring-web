package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.listUsers().stream()
                .map(mapper::parseEntityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        return mapper.parseEntityToDto(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectFewRecords() {
        User firstUser = new User(1L, "first", "user");
        User secondUser = new User(2L, "second", "user");
        User thirdUser = new User(3L, "third", "user");
        userService.add(firstUser);
        userService.add(secondUser);
        userService.add(thirdUser);
        return "Users were created";
    }
}
