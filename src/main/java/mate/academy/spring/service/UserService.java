package mate.academy.spring.service;

import java.util.List;
import mate.academy.spring.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    List<User> listUsers();
}
