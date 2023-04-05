package ru.etozhealexis.crudapplication.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        return "create";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }

        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));

        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);

        return "redirect:/users";
    }
}
