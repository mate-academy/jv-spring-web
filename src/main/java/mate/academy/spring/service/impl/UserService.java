package mate.academy.spring.service.impl;

import java.util.List;
import mate.academy.spring.model.User;

public interface UserService {
    void add(User user);

    List<User> getAll();

    User get(Long id);
}
