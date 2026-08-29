import mate.academy.spring.config.AppConfig;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User doe = new User("John","Doe");
        User emily = new User("Emily","Stone");
        User hugh = new User("Hugh","Dane");
        userService.add(doe);
        userService.add(emily);
        userService.add(hugh);
        System.out.println(userService.getAll());
    }
}
