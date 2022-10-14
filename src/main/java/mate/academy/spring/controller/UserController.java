package mate.academy.spring.controller;

import mate.academy.spring.dao.UserDao;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.mapper.UserDtoMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping//проверка без литерала тоже будет работать или нет
public class UserController {
    private UserService userService;
    private UserDtoMapper dtoMapper;

    @Autowired
    public UserController(UserService userService,
                          UserDtoMapper dtoMapper) {
        this.userService = userService;
        this.dtoMapper = dtoMapper;
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

        userService.add(john);
        userService.add(emily);
        userService.add(hugh);
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return dtoMapper.getDto(userService.get(id));
    }

    @GetMapping("/users")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(dtoMapper::getDto)
                .collect(Collectors.toList());
    }


}
