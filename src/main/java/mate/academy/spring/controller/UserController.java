package mate.academy.spring.controller;

import java.util.ArrayList;
import java.util.List;
import mate.academy.spring.dto.UserResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

/*
    @GetMapping("/users/inject")
    public void get() {

    }
    @GetMapping("/users/{userId}")
    public UserResponseDto get(Long userId) {

        return new UserResponseDto();
    }

    @GetMapping("/users/")
    public List<UserResponseDto> getAll() {

        return new ArrayList<UserResponseDto>();
    }

 */
}
