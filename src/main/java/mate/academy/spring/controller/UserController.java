package mate.academy.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServlet;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.impl.UserService;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/users")
public class UserController extends HttpServlet {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Autowired
    public UserController(UserService userService, UserDtoMapper userDtoMapper) {
        this.userService = userService;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("")
    public List<UserResponseDto> getAllUser(Model model) {
        return userService.getAll().stream()
                .map(UserDtoMapper::parse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return UserDtoMapper.parse(userService.get(id));

    }

    @GetMapping("/inject")
    public String injectMockData() {
        userService.add(createUser("John", "Doe"));
        userService.add(createUser("Emily", "Stone"));
        userService.add(createUser("Hugh", "Dane"));
        return "Users are injected!";
    }

    private User createUser(String name, String lastname) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastname);
        return user;
    }
}
