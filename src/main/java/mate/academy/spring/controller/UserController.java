package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dao.UserDao;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserDao userDao, UserDtoMapper userDtoMapper) {
        this.userDao = userDao;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String injectMockUsers(Model model) {
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
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userDao.get(userId).get());
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userDao.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
