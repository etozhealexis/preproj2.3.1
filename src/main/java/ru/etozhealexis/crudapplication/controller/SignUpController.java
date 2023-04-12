package ru.etozhealexis.crudapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.etozhealexis.crudapplication.model.User;
import ru.etozhealexis.crudapplication.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    UserService userService;

    @Autowired
    protected AuthenticationManager authenticationManager;


    @GetMapping
    public String signUp(@ModelAttribute("user") User user) {
        return "signup";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        userService.saveUser(user);

//        authenticateUser(user, request);

        return "redirect:/login";
    }

//    maybe toDO: auto-login after registration
//    private void authenticateUser(User user, HttpServletRequest request) {
//        String username = user.getEmail();
//        String password = user.getPassword();
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authenticatedUser = authenticationManager.authenticate(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
//    }
}
