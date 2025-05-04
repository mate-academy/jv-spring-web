package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserControllerImpl(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/users/inject")
    public String bootstrapData() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");
        userService.add(john);

        User emily = new User();
        emily.setFirstName("Emily");
        emily.setLastName("Stone");
        userService.add(emily);

        User hugh = new User();
        hugh.setFirstName("Hugh");
        hugh.setLastName("Dane");
        userService.add(hugh);

        return "Users are injected!";
    }

    @Override
    @GetMapping("/users/{userId}")
    @ResponseBody
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @Override
    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(user -> userDtoMapper.parse(user))
                .collect(Collectors.toList());
    }
}
