package ru.etozhealexis.crudapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.etozhealexis.crudapplication.dao.UserDao;
import ru.etozhealexis.crudapplication.models.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}
