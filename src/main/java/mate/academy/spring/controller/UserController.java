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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String injectUsers() {
        User leonardo = new User();
        leonardo.setName("Leo");
        leonardo.setLastName("Nardo");
        userService.add(leonardo);

        User donatello = new User();
        donatello.setName("Dona");
        donatello.setLastName("Tello");
        userService.add(donatello);

        User rafael = new User();
        rafael.setName("Raf");
        rafael.setLastName("Ael");
        userService.add(rafael);

        User michelangelo = new User();
        michelangelo.setName("Michel");
        michelangelo.setLastName("Angelo");
        userService.add(michelangelo);

        return "Teenage mutant ninja turtles coming...";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
