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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @ResponseBody
    @GetMapping("/inject")
    public String inject() {
        User yurka = new User();
        yurka.setName("yurii");
        userService.add(yurka);
        return yurka.toString();
    }

    @ResponseBody
    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(UserDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return UserDtoMapper.parse(userService.get(userId));
    }
}
