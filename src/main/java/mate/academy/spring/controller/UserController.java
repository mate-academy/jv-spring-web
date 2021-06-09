package mate.academy.spring.controller;

import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/inject")
    public String inject() {
        User userBob = new User();
        userBob.setLastName("Chupika");
        userBob.setName("Bobby");

        User userAlice = new User();
        userAlice.setLastName("Chupika");
        userAlice.setName("Alice");

        User userJohn = new User();
        userJohn.setLastName("Chupika");
        userJohn.setName("John");

        userService.add(userBob);
        userService.add(userAlice);
        userService.add(userJohn);

        return "Done";
    }

    @GetMapping("/users/{userId}")
    UserResponseDto get(Long userId) {
        return new UserResponseDto();
    }

    @GetMapping("/users")
    List<UserResponseDto> getAll() {
        return new ArrayList<>();
    }
}
