package ru.etozhealexis.crudapplication.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.etozhealexis.crudapplication.models.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManager.createNamedQuery("getUsers")
                .getResultList();
    }

    @Override
    public User getUser(int id) {
        return (User) entityManager.createNamedQuery("getUser")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }
}
