package com.example.pai_lab6.controller;

import com.example.pai_lab6.models.User;
import com.example.pai_lab6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repo;

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
    public String registerPagePOST(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        //przekierowanie do adresu url: /login
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

    @GetMapping("/profile/edit")
    public String editProfilePage(Model m, Principal principal) {
        User user = repo.findByLogin(principal.getName());
        m.addAttribute("user", user);
        return "edit"; // view name for the profile edit form
    }

    // Method to handle the form submission for updating user data
    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute User updatedUser, Principal principal) {
        User user = repo.findByLogin(principal.getName());
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());

        // Only update the password if it's been changed
        if (!updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        repo.save(user);
        return "redirect:/profile"; // redirect to profile page after update
    }

    // Method to delete the logged-in user's account
    @PostMapping("/profile/delete")
    public String deleteAccount(Principal principal) {
        User user = repo.findByLogin(principal.getName());
        if (user != null) {
            repo.delete(user);
        }
        SecurityContextHolder.clearContext();
        return "redirect:/logout";
    }


}
