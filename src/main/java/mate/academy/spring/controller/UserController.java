package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String injectUsers(Model model) {
        User john = createUser("John", "Doe");
        User emily = createUser("Emily", "Stone");
        User hugh = createUser("Hugh", "Dane");

        userService.add(john);
        userService.add(emily);
        userService.add(hugh);

        model.addAttribute("message", "Users are injected!");
        return "Users are injected!";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    private UserResponseDto getUser(@PathVariable("userId") Long userId) {
        return userMapper.mapUserToResponseDto(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::mapUserToResponseDto)
                .collect(Collectors.toList());
    }

    private static User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
