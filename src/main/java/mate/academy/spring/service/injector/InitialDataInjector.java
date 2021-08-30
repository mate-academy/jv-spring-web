package mate.academy.spring.service.injector;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitialDataInjector {
    private UserService userService;

    @PostConstruct
    public void injectInitialData() {
        userService.add(new User("Peter", "Parker"));
        userService.add(new User("Michael", "Jackson"));
        userService.add(new User("Marco", "Hietala"));
    }
}
