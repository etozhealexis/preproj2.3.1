package ru.etozhealexis.crudapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.etozhealexis.crudapplication.model.User;
import ru.etozhealexis.crudapplication.repository.RoleRepository;
import ru.etozhealexis.crudapplication.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public List<User> listUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public String listUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.getUser(id));

        return "users";
    }

    @PostMapping("/new")
    public void create(@RequestBody @Valid User user) {
        userService.saveUser(user);
    }

    @GetMapping("/edit/{id}")
    public User update(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/edit/{id}")
    public void saveUpdate(@RequestBody User user) {
        userService.update(user.getId(), user);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

}
