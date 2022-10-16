package mate.academy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("custom_title", "Custom Title");
        model.addAttribute("custom_header", "Custom header");
        return "index";
    }
}
