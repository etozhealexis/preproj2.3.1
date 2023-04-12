package ru.etozhealexis.crudapplication.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.etozhealexis.crudapplication.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManager.createNamedQuery("getUsers")
                .getResultList();
    }

    @Override
    public User getUser(Long id) {
        return (User) entityManager.createNamedQuery("getUser")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(Long id, User updatedUser) {
        User userToUpdate = getUser(id);
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setAge(updatedUser.getAge());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPassword(updatedUser.getPassword());
        userToUpdate.setRoles(updatedUser.getRoles());
        entityManager.merge(userToUpdate);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

    @Override
    public UserDetails getUserByEmail(String email) {
        return (User) entityManager.createNamedQuery("getUserByEmail")
                .setParameter("email", email)
                .getSingleResult();
    }
}
