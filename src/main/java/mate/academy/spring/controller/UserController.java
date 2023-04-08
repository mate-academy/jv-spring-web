package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @ResponseBody
    @GetMapping("/users")
    public List<UserResponseDto> main() {
        return userService.getAll().stream().map(userDtoMapper::parse).collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/users/inject")
    public String inject() {
        User johnDoe = new User();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        User emilyStone = new User();
        emilyStone.setFirstName("Emily");
        emilyStone.setLastName("Stone");
        User hughDane = new User();
        hughDane.setFirstName("Hugh");
        hughDane.setLastName("Dane");
        userService.add(johnDoe);
        userService.add(emilyStone);
        userService.add(hughDane);
        return "Users are injected!";
    }

    @ResponseBody
    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }
}
