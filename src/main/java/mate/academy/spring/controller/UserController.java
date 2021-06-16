package mate.academy.spring.controller;

import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ResponseBody
    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.mapFromUserToDto(userService.get(userId));
    }

    @ResponseBody
    @GetMapping("/users/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::mapFromUserToDto)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/users/inject")
    public String injectMockData() {
        User bobRoss = new User("Bob", "Ross");
        User samuelHayden = new User("Samuel", "Hayden");
        User willSmith = new User("Will", "Smith");
        User harleenThompson = new User("Harleen", "Thompson");
        User chrisRedfield = new User("Chris", "Redfield");
        userService.add(bobRoss);
        userService.add(samuelHayden);
        userService.add(willSmith);
        userService.add(harleenThompson);
        userService.add(chrisRedfield);
        return "Added mock data to DB!";
    }
}
