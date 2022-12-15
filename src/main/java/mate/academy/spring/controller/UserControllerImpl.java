package mate.academy.spring.controller;

import mate.academy.spring.dao.UserDao;
import mate.academy.spring.model.dto.UserResponseDto;
import mate.academy.spring.service.mapper.UserDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserControllerImpl implements UserController {
    private UserDao userDao;
    private UserDtoMapper userDtoMapper;

    @Autowired
    public UserControllerImpl(UserDao userDao, UserDtoMapper userDtoMapper) {
        this.userDao = userDao;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    @GetMapping("/{userId}")
    @ResponseBody
    public UserResponseDto get(@PathVariable Long userId) {
        return null;
    }

    @Override
    @GetMapping("/")
    public List<UserResponseDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> userDtoMapper.parse(user))
                .collect(Collectors.toList());
    }
}
