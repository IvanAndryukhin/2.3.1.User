package net.javacode.dao;

import net.javacode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    @Autowired
    UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User existingUser = entityManager.find(User.class, user.getId());
        if (existingUser == null) {
            throw new EntityNotFoundException("User not found with id: " + user.getId());
        }
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAdress(user.getAdress()); // Проверьте правильность написания. Может быть "address"?
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user); // Удаляем объект user, а не его id
        } else {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User getUserById(Long id) {
        if (id != null) {
            return entityManager.find(User.class, id);
        } else {
            throw new EntityNotFoundException("User id cannot be null");
        }
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}

