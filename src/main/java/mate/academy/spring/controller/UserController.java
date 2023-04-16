package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
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
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/inject")
    public String inject() {
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

    @GetMapping()
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(user ->
                userMapper.parse(user)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

}
