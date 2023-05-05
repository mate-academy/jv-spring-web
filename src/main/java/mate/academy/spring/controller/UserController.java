package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
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

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String usersInjectMockData() {
        User userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userService.add(userJohn);
        User userEmily = new User();
        userJohn.setFirstName("Emily");
        userJohn.setLastName("Stone");
        userService.add(userEmily);
        User userHugh = new User();
        userJohn.setFirstName("Hugh");
        userJohn.setLastName("Dane");
        userService.add(userHugh);
        return "Users are injected!";
    }
}
