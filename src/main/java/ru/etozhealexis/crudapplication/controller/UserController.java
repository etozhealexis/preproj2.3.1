package ru.etozhealexis.crudapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.etozhealexis.crudapplication.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String greeting(Model model, Authentication authentication) {
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("roles", authentication.getAuthorities());

        return "hello";
    }
}
