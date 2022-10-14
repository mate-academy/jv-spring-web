package mate.academy.spring.controller;

import mate.academy.spring.dao.UserDao;
import mate.academy.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping//проверка без литерала тоже будет работать или нет
public class UserController {
    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users/inject")
    public void saveUsers() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");
        User emily = new User();
        emily.setFirstName("Emily");
        emily.setLastName("Stone");
        User hugh = new User();
        hugh.setFirstName("Hugh");
        hugh.setLastName("Dane");

        userDao.add(john);
        userDao.add(emily);
        userDao.add(hugh);
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto getUserById(@PathVariable Long id) {

    }


}
