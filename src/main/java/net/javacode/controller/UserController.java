package net.javacode.controller;


import net.javacode.model.User;
import net.javacode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user")  User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_user";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
