package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.mappers.UserMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public void get() {
        User shrek = new User();
        shrek.setName("Shrek");
        shrek.setLastName("Shrekovich");
        userService.add(shrek);

        User donkey = new User();
        donkey.setLastName("Donkey");
        donkey.setName("Donkevich");
        userService.add(donkey);

        User fiona = new User();
        fiona.setName("Fiona");
        fiona.setLastName("Fionivna");
        userService.add(fiona);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getById(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
}
