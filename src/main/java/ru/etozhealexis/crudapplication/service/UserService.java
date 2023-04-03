package ru.etozhealexis.crudapplication.service;

import ru.etozhealexis.crudapplication.models.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(int id);

    void saveUser(User user);
}
