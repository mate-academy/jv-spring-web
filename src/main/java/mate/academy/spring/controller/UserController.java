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
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User userBob = new User();
        userBob.setName("Bob");
        userBob.setLastName("Last Names Bob");
        User userAlisa = new User();
        userAlisa.setName("Alisa");
        userAlisa.setLastName("Last Names Alisa");
        userService.add(userBob);
        userService.add(userAlisa);
        User userIvan= new User();
        userAlisa.setName("Ivan");
        userAlisa.setLastName("Last Names Ivan");
        userService.add(userBob);
        userService.add(userAlisa);
        return "Adding users is done!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/")
    List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(a -> userDtoMapper.parse(a))
                .collect(Collectors.toList());
    }
}
