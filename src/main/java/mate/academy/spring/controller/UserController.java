package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    private UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return mapper.parseToDto(userService.get(userId));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(mapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/inject")
    public String injectData() {
        User slava = new User();
        slava.setFirstName("slava");
        slava.setLastName("sukhov");

        User carolina = new User();
        carolina.setFirstName("carolina");
        carolina.setLastName("sukhova");

        User anna = new User();
        anna.setFirstName("anna");
        anna.setLastName("sukhova");

        userService.add(slava);
        userService.add(carolina);
        userService.add(anna);

        return "Users are injected!";
    }
}
