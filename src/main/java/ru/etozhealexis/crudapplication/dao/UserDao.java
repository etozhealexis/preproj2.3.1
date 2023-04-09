package ru.etozhealexis.crudapplication.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.etozhealexis.crudapplication.models.User;

import java.util.List;

public interface UserDao {
    @SuppressWarnings("unchecked")
    List<User> getUsers();

    User getUser(Long id);

    void saveUser(User user);

    void update(Long id, User user);

    void delete(Long id);

    UserDetails getUserByEmail(String email);
}
