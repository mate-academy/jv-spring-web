package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserMapper;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/inject")
    public String create(Model model) {
        User petro = new User();
        petro.setName("Petro");
        petro.setLastName("Petrykovskyi");
        User kateryna = new User();
        kateryna.setName("Kateryna");
        kateryna.setLastName("Katerynchuk");
        User ivanna = new User();
        ivanna.setName("Ivanna");
        ivanna.setLastName("Ivanova");
        List.of(petro, kateryna, ivanna).forEach(userService::add);
        model.addAttribute("message", "Users added successfully!");
        return "index";
    }

    @ResponseBody
    @GetMapping("/users/")
    public List<UserResponseDto> getUsers() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/users/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userMapper.parse(userService.get(userId));
    }
}
