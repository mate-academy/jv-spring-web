package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService,
                          UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.getDtoFromUser(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(userDtoMapper::getDtoFromUser)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectData() {
        User userBob = new User();
        userBob.setName("Bob");
        userBob.setLastName("Bobikovich");
        userService.add(userBob);

        User userAlice = new User();
        userAlice.setName("Alice");
        userAlice.setLastName("Lisa");
        userService.add(userAlice);

        User userMax = new User();
        userMax.setName("Max");
        userMax.setLastName("Fishin");
        userService.add(userMax);
        return "Injected users";
    }
}
