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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;
    
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }
    
    @ResponseBody
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }
    
    @ResponseBody
    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream().map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/inject")
    public String injectMockUser() {
        User user1 = new User();
        user1.setName("Vovan");
        user1.setLastName("Salli");
        
        User user2 = new User();
        user2.setName("Emily");
        user2.setLastName("Clark");
        
        User user3 = new User();
        user3.setName("Daniel");
        user3.setLastName("Sanz");
        
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        return "Data injected";
        
    }
}
