package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserRequestDto;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String inject() {
        List<UserRequestDto> dtos = formListForInject();
        List<User> users = dtos.stream()
                .map(userDtoMapper::toModel)
                .collect(Collectors.toList());
        for (User user : users) {
            userService.add(user);
        }
        return "Users are injected!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("/find") //?name=John
    public List<UserResponseDto> getAllByName(@RequestParam String name) {
        return userService.findByName(name).stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    private List<UserRequestDto> formListForInject() {
        UserRequestDto john = new UserRequestDto();
        john.setFirstName("John");
        john.setLastName("Doe");
        UserRequestDto emily = new UserRequestDto();
        emily.setFirstName("Emily");
        emily.setLastName("Stone");
        UserRequestDto hugh = new UserRequestDto();
        hugh.setFirstName("Hugh");
        hugh.setLastName("Dane");
        return List.of(john, emily, hugh);
    }
}
