package mate.academy.spring.service;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.User;

public interface UserService {
    void add(User user);

    Optional<User> get(Long id);

    List<User> listUsers();
}
