package mate.academy.spring.controller;

import java.util.List;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserResponceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final UserResponceDtoMapper mapper;

    @Autowired
    public UserController(UserService userService, UserResponceDtoMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return mapper.userListToUserResponceDtoList(userService.getAll());
    }

    @GetMapping("/inject")
    public String inject() {
        userService.add(new User("Bob", "Corn"));
        userService.add(new User("Jack", "Black"));
        userService.add(new User("Alice", "Mason"));
        return "done!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable(value = "id") Long userId) {
        return mapper.userToUserResponceDto(userService.get(userId));
    }

}
