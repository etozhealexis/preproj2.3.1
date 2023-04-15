package ru.etozhealexis.crudapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.etozhealexis.crudapplication.service.UserService;

@Controller()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("roles", authentication.getAuthorities());

        return "admin";
    }
}
