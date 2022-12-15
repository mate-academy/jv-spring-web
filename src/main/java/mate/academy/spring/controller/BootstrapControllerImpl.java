package mate.academy.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootstrapControllerImpl implements BootstrapController {
    @Override
    @GetMapping("/users/inject")
    public String bootstrapData() {
        return null;
    }
}
