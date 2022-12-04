package mate.academy.spring.controller;

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
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User firstOne = new User();
        firstOne.setFirstName("John");
        firstOne.setLastName("Doe");
        userService.add(firstOne);

        User secondOne = new User();
        secondOne.setFirstName("Emily");
        secondOne.setLastName("Stone");
        userService.add(secondOne);

        User thirdOne = new User();
        thirdOne.setFirstName("Hugh");
        thirdOne.setLastName("Dane");
        userService.add(thirdOne);
        return "Users are injected!";
    }

    @GetMapping()
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parseToDto(userService.get(userId));
    }
}
