package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/inject")
    @ResponseBody
    public String inject() {
        User user1 = new User("John", "Doe");
        User user2 = new User("Emily", "Stone");
        User user3 = new User("Hugh", "Dane");
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        return "Users are injected!";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserResponseDto get(@PathVariable String id) {
        return userMapper.toUserDto(userService.get(Long.valueOf(id)));
    }

    @GetMapping("")
    @ResponseBody
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
