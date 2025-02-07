package net.javacode.controller;

import net.javacode.dao.UserDaoImpl;
import net.javacode.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDaoImpl userDao;

    public UserController(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userDao.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userDao.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam Long id, Model model) {
        User user = userDao.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit_user";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userDao.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userDao.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/getById")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        User user = userDao.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}



