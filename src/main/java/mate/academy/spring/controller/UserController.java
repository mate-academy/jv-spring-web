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

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.parse(userService.get(userId));
    }

    @GetMapping("/inject")
    public String injectMockData() {
        User bobMarley = new User();
        bobMarley.setName("Bob");
        bobMarley.setLastName("Marley");

        User bobDylan = new User();
        bobDylan.setName("Bob");
        bobDylan.setLastName("Dylan");

        User freddie = new User();
        freddie.setName("Freddie");
        freddie.setLastName("Mercury");

        User ozzy = new User();
        ozzy.setName("Ozzy");
        ozzy.setLastName("Osbourne");

        userService.add(bobMarley);
        userService.add(bobDylan);
        userService.add(freddie);
        userService.add(ozzy);
        return "Done!";
    }
}
