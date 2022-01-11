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
        danya.setName("Danechka");
        danya.setLastName("Nesterov");

        User andrew = new User();
        andrew.setName("Andrewshechka");
        andrew.setLastName("Dzundza");

        User paul = new User();
        paul.setName("Pashecka");
        paul.setLastName("Martsiniv");

        User ilya = new User();
        ilya.setName("Ilyshecka");
        ilya.setLastName("Lavryniuk");

        User alex = new User();
        alex.setName("Sanyechka");
        alex.setLastName("Moskovchuk");

        userService.add(danya);
        userService.add(andrew);
        userService.add(paul);
        userService.add(ilya);
        userService.add(alex);
        return "Done";
    }
}
