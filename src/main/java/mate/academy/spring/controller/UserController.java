package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dao.dto.UserResponseDto;
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

    private final UserService userService;
    private final UserDtoMapper userDtoMapper = new UserDtoMapper();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream().map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/inject")
    public String injectUser() {
        User ivanov = new User("Ivan","Ivanov");
        User petrov = new User("Petro", "Petrov");
        User sidorov = new User("Sidor", "Sidorov");
        userService.add(ivanov);
        userService.add(petrov);
        userService.add(sidorov);
        return "Done !";
    }
}

