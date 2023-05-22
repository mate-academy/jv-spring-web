package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserDtoMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;
    
    @Autowired
    public UserController(UserService userService,
            UserDtoMapper userMapper) {
        this.userService = userService;
        this.userDtoMapper = userMapper;
    }
    
    @ResponseBody
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
    
    @ResponseBody
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }
    
    @ResponseBody
    @GetMapping("/inject")
    public String inject() {
        User userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userService.add(userJohn);
        
        User userEmily = new User();
        userEmily.setFirstName("Emily");
        userEmily.setLastName("Stone");
        userService.add(userEmily);

        User userHugh = new User();
        userHugh.setFirstName("Hugh");
        userHugh.setLastName("Dane");
        userService.add(userHugh);

        return "Users are injected!";
    }
}
