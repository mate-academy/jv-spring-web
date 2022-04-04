package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.model.service.UserService;
import mate.academy.spring.model.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService service, UserMapper userMapper) {
        this.service = service;
        this.userMapper = userMapper;
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");
        service.add(john);

        User emily = new User();
        emily.setFirstName("Emily");
        emily.setLastName("Stone");
        service.add(emily);

        User hugh = new User();
        hugh.setFirstName("Hugh");
        hugh.setLastName("Dane");
        service.add(hugh);

        return "Users are injected!";
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return service.getAll()
                .stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(service.get(id));
    }
}
