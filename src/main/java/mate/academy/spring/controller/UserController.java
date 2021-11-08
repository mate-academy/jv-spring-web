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
    private final UserDtoMapper userDtoMapper;

    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService
                .getAll()
                .stream()
                .map(userDtoMapper::mapModelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable Long userId) {
        return userDtoMapper.mapModelToDto(userService.get(userId));
    }

    @GetMapping("/inject")
    public String injectMokData() {
        User bob = new User("bob", "pupicov");
        User vlad = new User("vlad", "gural");
        User alis = new User("alis", "krolchak");
        User tom = new User("tom", "vetrov");
        userService.add(bob);
        userService.add(vlad);
        userService.add(alis);
        userService.add(tom);
        return "Done!";
    }
}
