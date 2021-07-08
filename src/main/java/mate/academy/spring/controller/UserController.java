package mate.academy.spring.controller;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.UserMapper;
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

    @Autowired
    private UserService userService;

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
        return UserMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(UserMapper::parse)
                .collect(Collectors.toList());
    }
}
