package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return mapper.parseUserToDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(mapper::parseUserToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectUsers() {
        User firstUser = new User();
        firstUser.setFirstName("John");
        firstUser.setLastName("Doe");
        userService.add(firstUser);

        User secondUser = new User();
        secondUser.setFirstName("Emily");
        secondUser.setLastName("Stone");
        userService.add(secondUser);

        User thirdUser = new User();
        thirdUser.setFirstName("Hugh");
        thirdUser.setLastName("Dane");
        userService.add(thirdUser);

        return "Users are injected!";
    }
}
