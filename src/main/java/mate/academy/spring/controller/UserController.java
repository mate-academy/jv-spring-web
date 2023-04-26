package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserDtoMapper;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
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

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String index() {
        User vasia = new User();
        vasia.setFirstName("Vasia");
        vasia.setLastName("Vasilchenko");

        User marucia = new User();
        marucia.setFirstName("Marucia");
        marucia.setLastName("Marucieva");

        User petia = new User();
        petia.setFirstName("Petia");
        petia.setLastName("Petrov");

        userService.add(vasia);
        userService.add(marucia);
        userService.add(vasia);
        return "Users are injected!";
    }
}
