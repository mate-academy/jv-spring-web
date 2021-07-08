package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserDtoMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public void inject() {
        User ruslan = new User();
        ruslan.setName("Ruslan");
        ruslan.setLastName("Krylov");

        User vasia = new User();
        vasia.setName("Vasia");
        vasia.setLastName("Pupkin");

        User bla = new User();
        bla.setName("Bla");
        bla.setLastName("Bla-Bla");

        userService.add(ruslan);
        userService.add(vasia);
        userService.add(bla);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getById(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
