package com.gryta.pai_security.controllers;

import java.security.Principal;
import java.util.List;

import com.gryta.pai_security.User;
import com.gryta.pai_security.configuration.UserAuthenticationDetails;
import com.gryta.pai_security.dao.UserDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao repo;

    @Autowired
    private UserAuthenticationDetails userAuthenticationDetails;

    @GetMapping("/login")
    public String loginPage() {
        //zwrócenie nazwy widoku logowania - login.html
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model m) {
        //dodanie do modelu nowego użytkownika
        m.addAttribute("user", new User());
        //zwrócenie nazwy widoku rejestracji - register.html
        return "register";
    }
    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "register"; // powrót do rejestracji
        }

        User existingUser = repo.findByLogin(user.getLogin());
        if (existingUser != null) {
            binding.rejectValue("login", "error.user", "User with that login already exists");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return "redirect:/login";
    }
    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        //dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("user", repo.findByLogin(principal.getName()));
        //zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }
    @GetMapping("/users")
    public String allUsers(Model m, Principal principal) {
        List<User> users = repo.findAll();
        m.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/current/edit")
    public String editCurrentUser(Model m, Principal principal) {
        User user = repo.findByLogin(principal.getName());
        m.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping("/users/current/edit")
    public String updateCurrentUser(@Valid User user, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "user_edit"; // Return the view if there are validation errors
        }

        User currentUser = repo.findByLogin(principal.getName());
        User existingUser = repo.findByLogin(user.getLogin());
        if (existingUser != null && !existingUser.getUserid().equals(currentUser.getUserid()) && existingUser.getLogin().equals(user.getLogin())) {
            bindingResult.rejectValue("login", "error.user", "Login already exists");
            return "user_edit";
        }

        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());
        currentUser.setLogin(user.getLogin());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(currentUser);

        var userDetails = userAuthenticationDetails.loadUserByUsername(currentUser.getLogin());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/profile";
    }

    @GetMapping("/users/current/delete")
    public String deleteCurrentUser(Principal principal) {
        User user = repo.findByLogin(principal.getName());
        repo.delete(user);
        return "redirect:/logout";
    }


}
