package mate.academy.spring.controller;

import mate.academy.spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/users")
    public String testJsp(Model model) {
        model.addAttribute("message", "Test JSP.");
        return "users";
    }

    @ResponseBody
    @GetMapping("/get-user")
    public User testJsp() {
        return new User(1L, "John", "Doe");
    }
}
