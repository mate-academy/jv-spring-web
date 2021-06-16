package mate.academy.spring.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.spring.model.User;

public interface UserDao {
    User add(User user);

    List<User> getAll();

    User get(Long id);
}
