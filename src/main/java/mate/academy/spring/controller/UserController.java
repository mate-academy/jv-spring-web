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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService,
                          UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User danya = new User();
        danya.setName("Danya");
        danya.setLastName("Nesterov");
        User andrew = new User();
        andrew.setName("Andrew");
        andrew.setLastName("Dzundza");
        userService.add(danya);
        userService.add(andrew);
        return "Done";
    }
}
