package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
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
    private final UserDtoMapper userDtoMappers;

    public UserController(UserService userService, UserDtoMapper userDtoMappers) {
        this.userService = userService;
        this.userDtoMappers = userDtoMappers;
    }

    @GetMapping("/inject")
    public String inject() {
        String[] fullNames = new String[] {"John Doe", "Emily Stone", "Hugh Dane"};
        for (String fullName : fullNames) {
            String[] names = fullName.split(" ");
            User user = new User();
            user.setFirstName(names[0]);
            user.setLastName(names[1]);
            userService.add(user);
        }
        return "Users are injected!";
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMappers.parse(userService.get(userId));
    }

    @GetMapping("")
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMappers::parse)
                .collect(Collectors.toList());
    }
}
