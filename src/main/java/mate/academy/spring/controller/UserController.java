package mate.academy.spring.controller;

import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/inject")
    public String inject() {
        User one = new User();
        one.setFirstName("John");
        one.setLastName("Doe");
        userService.add(one);

        User two = new User();
        two.setFirstName("Emily");
        two.setLastName("Stone");
        userService.add(two);

        User three = new User();
        three.setFirstName("Hugh");
        three.setLastName("Dane");
        userService.add(three);

        return "Users are injected!";
    }


}
