package ru.etozhealexis.crudapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.etozhealexis.crudapplication.models.User;
import ru.etozhealexis.crudapplication.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    UserService userService;

    @GetMapping
    public String signUp(@ModelAttribute("user") User user) {
        return "signup";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.saveUser(user);

        return "redirect:/login";
    }
}
