package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parseUserToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseUserToDto(userService.get(userId));
    }

    @GetMapping("/inject")
    public String inject() {
        User userFirst = new User();
        userFirst.setFirstName("John");
        userFirst.setLastName("Doe");
        userService.add(userFirst);

        User userSecond = new User();
        userSecond.setFirstName("Emily");
        userSecond.setLastName("Stone");
        userService.add(userSecond);

        User userThird = new User();
        userThird.setFirstName("Hugh");
        userThird.setLastName("Dane");
        userService.add(userThird);

        return "Users are injected!";
    }
}
