package net.javacode.controller;

import net.javacode.dao.UserDaoImpl;
import net.javacode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {


    private final UserDaoImpl userDao;
    UserController(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userDao.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userDao.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userDao.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        User user = userDao.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userDao.getUsers();
        return ResponseEntity.ok(users);
    }
}
