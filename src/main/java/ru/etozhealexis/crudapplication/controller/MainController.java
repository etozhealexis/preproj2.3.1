package ru.etozhealexis.crudapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.etozhealexis.crudapplication.model.User;
import ru.etozhealexis.crudapplication.service.UserService;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
//    @GetMapping("/signup")
//    public String signUp(@ModelAttribute("user") User user) {
//        return "signup";
//    }
//
//    @PostMapping
//    public String create(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "signup";
//        }
//
//        userService.saveUser(user);
//
//        return "redirect:/login";
//    }

}
