package net.javacode.service;

import net.javacode.model.User;

import java.util.List;

public interface UserService {
     void addUser(User user);
     void updateUser(User user);
    void deleteUser(Long id);
     User getUserById(Long id);
     List<User> getUsers();
}
