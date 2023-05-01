package mate.academy.spring.controller;

import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Hello");
        return "index";
    }
}
