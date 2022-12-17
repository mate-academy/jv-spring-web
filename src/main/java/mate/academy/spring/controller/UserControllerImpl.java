package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.dto.UserResponseDto;
import mate.academy.spring.model.User;
import mate.academy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/inject")
    @Override
    public String injectMockData() {
        userService.add(new User("John", "Doe"));
        userService.add(new User("Emily", "Stone"));
        userService.add(new User("Hugh", "Dane"));
        return "Users are injected!";
    }

    @GetMapping("/users/{userId}")
    @Override
    public UserResponseDto get(@PathVariable Long userId) {
        User user = userService.get(userId);
        return getMappedDto(user);
    }

    @GetMapping("/users")
    @Override
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(UserControllerImpl::getMappedDto)
                .collect(Collectors.toList());
    }

    private static UserResponseDto getMappedDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }
}
