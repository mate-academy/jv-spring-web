package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping()
    public List<UserResponseDto> getAllUser() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/inject")
    public String injectMockUsers() {
        User firstTestUser = new User();
        firstTestUser.setFirstName("John");
        firstTestUser.setLastName("Doe");
        userService.add(firstTestUser);

        User secondTestUser = new User();
        secondTestUser.setFirstName("Emily");
        secondTestUser.setLastName("Stone");
        userService.add(secondTestUser);

        User thirdTestUser = new User();
        thirdTestUser.setFirstName("Hugh");
        thirdTestUser.setLastName("Dane");
        userService.add(thirdTestUser);
        return "Users are injected!";
    }
}
