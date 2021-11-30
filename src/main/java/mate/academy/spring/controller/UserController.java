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
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("inject")
    public String injectMockData() {
        User petro = new User();
        petro.setName("Petro");
        petro.setLastName("Petrovich");
        User anakin = new User();
        anakin.setName("Anakin");
        anakin.setLastName("Skywalker");
        User bilbo = new User();
        bilbo.setName("Bilbo");
        bilbo.setLastName("Begins");
        userService.add(petro);
        userService.add(bilbo);
        userService.add(anakin);
        return "All users are in DB!";
    }
}
