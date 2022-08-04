package mate.academy.spring.controller;

import java.util.Arrays;
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
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        String[][] data = {{"John", "Doe"}, {"Emily", "Stone"}, {"Hugh", "Dane"}};
        Arrays.stream(data).forEach(userData -> {
            User user = new User();
            user.setFirstName(userData[0]);
            user.setLastName(userData[1]);
            userService.add(user);
        });
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return UserMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(UserMapper::parse)
                .collect(Collectors.toList());
    }
}
