package mate.academy.spring.service;

import mate.academy.spring.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> getAll();

    User get(Long id);
}
