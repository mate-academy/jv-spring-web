package mate.academy.spring.service;

import java.util.List;
import mate.academy.spring.model.User;

public interface UserService {
    void add(User user);

    public User getById(Long id);

    List<User> getAll();
}
