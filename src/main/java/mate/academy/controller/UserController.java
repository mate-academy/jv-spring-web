package mate.academy.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ResponseBody
    @GetMapping("/users/inject")
    public String inject() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");

        User emily = new User();
        emily.setFirstName("Emily");
        emily.setLastName("Stone");

        User hugh = new User();
        hugh.setFirstName("Hugh");
        hugh.setLastName("Dane");

        userService.add(john);
        userService.add(emily);
        userService.add(hugh);

        return "Users are injected!";
    }

    @ResponseBody
    @GetMapping("users/{userId}")
    public UserResponseDto get(@PathVariable String userId) {
        return userMapper.parse(userService.get(Long.valueOf(userId)));
    }

    @ResponseBody
    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
