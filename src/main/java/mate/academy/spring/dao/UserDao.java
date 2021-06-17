package mate.academy.spring.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.User;

public interface UserDao {
    void add(User user);

    Optional<User> getById(Long id);

    List<User> getAll();
}
