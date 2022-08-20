package mate.academy.spring;

import mate.academy.spring.config.AppConfig;
import mate.academy.spring.dao.UserDaoImpl;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        User user = new User();
        user.setFirstName("Maksym");
        user.setLastName("Dibrov");
        userService.add(user);
    }
}
