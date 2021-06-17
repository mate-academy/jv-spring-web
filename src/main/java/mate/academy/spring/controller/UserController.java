package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserResponseDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserResponseDtoMapper userResponseDtoMapper;
    private final UserService userService;

    public UserController(UserResponseDtoMapper userResponseDtoMapper, UserService userService) {
        this.userResponseDtoMapper = userResponseDtoMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userResponseDtoMapper.parse(userService.get(id));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userResponseDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        User chris = new User("Chris", "Pain");
        userService.add(chris);

        User spenser = new User("Spenser", "Hals");
        userService.add(spenser);

        User morgan = new User("Morgan", "Battler");
        userService.add(morgan);
        return "All were injected!";
    }

}
