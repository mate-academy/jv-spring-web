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

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userDtoMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parseToDto(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectTestData() {
        User peter = new User();
        peter.setName("Peter");
        peter.setLastName("Parker");

        User jason = new User();
        jason.setName("Jason");
        jason.setLastName("Statham");

        User bob = new User();
        bob.setName("Bob");
        bob.setLastName("Borisovich");

        userService.add(peter);
        userService.add(jason);
        userService.add(bob);

        return "Data was injected";
    }
}
