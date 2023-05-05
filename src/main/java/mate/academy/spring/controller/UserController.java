package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping("/users/inject")
    public String index() {
        userService.add(new User("John", "Doe"));
        userService.add(new User("Emily", "Stone"));
        userService.add(new User("Hugh", "Dane"));
        return "Users are injected!";
    }

    @ResponseBody
    @GetMapping("/users/{id}")
    public User getByUserId(@PathVariable Long id) {
        return userService.get(id);
    }

    @ResponseBody
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }
}
