package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userMapper.parse(userService.get(id));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User dana = new User();
        dana.setId(1L);
        dana.setName("Dana");
        dana.setLastName("Khromenko");

        User natalie = new User();
        natalie.setId(2L);
        natalie.setName("Natalie");
        natalie.setLastName("Didyk");

        User lia = new User();
        lia.setId(3L);
        lia.setName("Lia");
        lia.setLastName("Ladoda");

        userService.add(dana);
        userService.add(natalie);
        userService.add(lia);

        return "Done";
    }
}

