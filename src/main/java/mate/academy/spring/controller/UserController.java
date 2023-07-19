package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.maper.UserDtoMapper;
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

    @GetMapping("/{id}")
    UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping
    List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(userDtoMapper::parse).collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        User user2 = new User();
        user2.setFirstName("Emily");
        user2.setLastName("Stone");
        User user3 = new User();
        user3.setFirstName("Hugh");
        user3.setLastName("Dane");
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        return "Users are injected!";
    }
}
