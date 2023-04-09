package ru.etozhealexis.crudapplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.etozhealexis.crudapplication.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    User getUser(Long id);

    void saveUser(User user);

    void update(Long id, User user);

    void delete(Long id);
}
