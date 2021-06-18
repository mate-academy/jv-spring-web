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
        return userService.listUsers().stream()
                .map(userDtoMapper::parseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userDtoMapper.parseToDto(userService.get(id));
    }

    @GetMapping("/inject")
    public String injectTestData() {
        User vin = new User();
        vin.setName("Vin");
        vin.setLastName("Diesel");

        User paul = new User();
        paul.setName("Paul");
        paul.setLastName("Walker");

        User michelle = new User();
        michelle.setName("Michelle");
        michelle.setLastName("Rodriguez");

        userService.add(vin);
        userService.add(paul);
        userService.add(michelle);

        return "Done";
    }
}
