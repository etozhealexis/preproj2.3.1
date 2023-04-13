package ru.etozhealexis.crudapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.etozhealexis.crudapplication.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    UserService userService;

//    @GetMapping
//    public String greeting(Model model, Authentication authentication) {
//        model.addAttribute("user", authentication.getPrincipal());
//        model.addAttribute("roles", authentication.getAuthorities());
//
//        return "hello";
//    }
}
