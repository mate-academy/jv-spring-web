package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User john = new User();
        john.setName("John");
        john.setLastName("Travolta");
        userService.add(john);
        User lucy = new User();
        lucy.setName("Lucy");
        lucy.setName("Brown");
        userService.add(lucy);
        User travis = new User();
        travis.setName("Travis");
        travis.setLastName("Scott");
        userService.add(travis);
        return "Successfully added";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parseToDto(userService.get(id));
    }

    @GetMapping("/")
    public List<UserResponseDto> getAllUsers() {
        return userService.listUsers().stream()
                .map(userDtoMapper::parseToDto)
                .collect(Collectors.toList());
    }
}
