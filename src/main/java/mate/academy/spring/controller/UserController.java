package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Petro");
        user1.setLastName("Petrenko");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Ivan");
        user2.setLastName("Ivanenko");

        User user3 = new User();
        user3.setId(3L);
        user3.setName("Oksana");
        user3.setLastName("Sydorenko");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);

        return "Done!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }
}
