package ru.etozhealexis.crudapplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.etozhealexis.crudapplication.model.Role;
import ru.etozhealexis.crudapplication.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<User> getUsers();

    User getUser(Long id);

    void saveUser(User user);

    void update(Long id, User user);


    void delete(Long id);

}
