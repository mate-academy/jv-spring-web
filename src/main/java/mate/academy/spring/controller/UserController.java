package mate.academy.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import mate.academy.spring.dao.UserDao;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserDao userDao;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserDao userDao,
                          UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDao.get(userId)
                .map(userMapper::mapFromEntityToDto)
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id "
                        + userId + "."));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userDao.getAll().stream()
                .map(userMapper::mapFromEntityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/inject")
    public String injectSampleData() {
        List<User> users = new ArrayList<>();
        User userJohnDoe = new User();
        userJohnDoe.setFirstName("John");
        userJohnDoe.setLastName("Doe");
        users.add(userJohnDoe);
        User userEmilyStone = new User();
        userEmilyStone.setFirstName("Emily");
        userEmilyStone.setLastName("Stone");
        users.add(userEmilyStone);
        User userHughDane = new User();
        userHughDane.setFirstName("Hugh");
        userHughDane.setLastName("Dane");
        users.add(userHughDane);
        users.forEach(userDao::add);
        return "Users are injected!";
    }

}
