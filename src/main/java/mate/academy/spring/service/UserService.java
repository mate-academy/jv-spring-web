package mate.academy.spring.service;

import java.util.List;
import mate.academy.spring.model.User;

public interface UserService {
    void add(User user);
    
    User getById(Long id);

    List<User> getAllUsers();
}
