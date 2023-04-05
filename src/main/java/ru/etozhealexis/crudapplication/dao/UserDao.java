package ru.etozhealexis.crudapplication.dao;

import ru.etozhealexis.crudapplication.models.User;

import java.util.List;

public interface UserDao {
    @SuppressWarnings("unchecked")
    List<User> getUsers();

    User getUser(int id);

    void saveUser(User user);

    void update(int id, User user);

    void delete(int id);
}
