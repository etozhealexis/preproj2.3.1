package ru.etozhealexis.crudapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.etozhealexis.crudapplication.models.User;
import ru.etozhealexis.crudapplication.service.UserService;

@Controller()
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());

        return "users";
    }

    @GetMapping("/{id}")
    public String listUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userService.getUser(id));

        return "users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));

        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                           @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }
}
