package mate.academy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("")
    public String inject() {

    }

    @GetMapping("/{userId}")
    public UserResponseDto get(Long userId) {

    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {

    }
}
