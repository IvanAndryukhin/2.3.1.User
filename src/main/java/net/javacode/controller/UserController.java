package net.javacode.controller;

import net.javacode.model.User;
import net.javacode.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Показываю юзеров и создаю таблицу
    @GetMapping("/")
    public String showUsersTable(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

    @GetMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user) {
        return "new_user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    //Изменение юзера

    @GetMapping("/edit")
    public String editUser(@RequestParam int id, Model model) {
        userService.getUser(id);
        model.addAttribute("user", userService.getUser(id));
        return "edit_user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    //Удаление юзера


    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        this.userService.deleteUser(id);
        return "redirect:/";
    }
}





