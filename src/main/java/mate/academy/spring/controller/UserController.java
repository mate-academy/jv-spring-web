package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapping.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        userService.add(user1);
        User user2 = new User();
        user2.setFirstName("Emily");
        user2.setLastName("Stone");
        userService.add(user2);
        User user3 = new User();
        user3.setFirstName("Hugh");
        user3.setLastName("Dane");
        userService.add(user3);
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(u -> userDtoMapper.parse(u))
                .collect(Collectors.toList());
    }
}
