package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserDtoMapper userDtoMapper, UserService userService) {
        this.userDtoMapper = userDtoMapper;
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User userOne = new User();
        userOne.setName("Tom");
        userOne.setLastName("Holland");
        userService.add(userOne);
        User userTwo = new User();
        userTwo.setName("Harry");
        userTwo.setLastName("Potter");
        userService.add(userTwo);
        User userTree = new User();
        userTree.setName("Lord");
        userTree.setLastName("Voldemort");
        userService.add(userTree);
        return "Done.";
    }
}
