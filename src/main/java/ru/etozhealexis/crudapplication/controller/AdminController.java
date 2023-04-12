package ru.etozhealexis.crudapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.etozhealexis.crudapplication.model.Role;
import ru.etozhealexis.crudapplication.model.User;
import ru.etozhealexis.crudapplication.repository.RoleRepository;
import ru.etozhealexis.crudapplication.service.UserService;

import java.util.List;

@Controller()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String listUsers(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("roles", authentication.getAuthorities());

        return "users";
    }

    @GetMapping("/{id}")
    public String listUser(@PathVariable("id") Long id, Model model) {
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

        return "redirect:/admin";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roleList", roleList);

        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }
}
