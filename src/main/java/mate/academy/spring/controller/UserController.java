package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAllUsers().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userMapper.parse(userService.getById(userId));
    }
    
    @GetMapping("/inject")
    public String injectMockData() {
        User ben = new User();
        ben.setName("Ben");
        ben.setLastName("Dillendorf");
    
        User alex = new User();
        alex.setName("Alex");
        alex.setLastName("Wachovski");
    
        User helen = new User();
        helen.setName("Helen");
        helen.setLastName("Lockly");
    
        userService.add(ben);
        userService.add(alex);
        userService.add(helen);
        return "Cool!";
    }
}
