package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/inject")
    public String injectMockData() {
        List<User> users = List.of(
                User.builder().name("Aleksandr").lastName("Moskovchuk").build(),
                User.builder().name("Andrew").lastName("Dzundza").build(),
                User.builder().name("Dany").lastName("Nesterov").build(),
                User.builder().name("Eugene").lastName("Kozlov").build(),
                User.builder().name("Illia").lastName("Lavryniuk").build(),
                User.builder().name("Kateryna").lastName("Nishchenko").build());
        users.forEach(userService::add);
        return "Mock data created!";
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parse(userService.get(id));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream()
                .map(userDtoMapper::parse)
                .collect(Collectors.toList());
    }
}
