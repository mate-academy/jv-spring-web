package mate.academy.spring.controller;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.mapper.UserDtoMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        User roma = new User();
        roma.setName("Roma");
        roma.setLastName("Ruckonog");
        userService.add(roma);

        User ghora = new User();
        roma.setName("Ghora");
        roma.setLastName("Nogoruck");
        userService.add(ghora);

        User nyura = new User();
        roma.setName("Nyura");
        roma.setLastName("Tango");
        userService.add(nyura);

        return "Done!";
    }

    @GetMapping("/")
    public List<UserResponseDto> getAllUsers() {
        return userService
                .getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }
}
