package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @ResponseBody
    @GetMapping
    public List<UserResponseDto> getAll(Long userId) {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject(Model model) {
        User userSerhii = new User();
        userSerhii.setFirstName("John");
        userSerhii.setLastName("Doe");
        userService.add(userSerhii);
        User userDamir = new User();
        userDamir.setFirstName("Emily");
        userDamir.setLastName("Stone");
        userService.add(userDamir);
        User userMatvii = new User();
        userMatvii.setFirstName("Hugh");
        userMatvii.setLastName("Dane");
        userService.add(userMatvii);
        return "Users are injected!";
    }
}
