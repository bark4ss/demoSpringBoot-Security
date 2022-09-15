package com.example.demospringboot.controller;

import com.example.demospringboot.model.Role;
import com.example.demospringboot.model.User;
import com.example.demospringboot.repository.UserRepo;
import com.example.demospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class RegistrationController {

    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        //modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute User user, ModelAndView modelAndView) {
        User userFromDb = userService.findByName(user.getUsername());

        if (userFromDb != null) {
            modelAndView.addObject("message", "User exists!");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        user.setRoles(new HashSet<>(List.of(Role.USER)));
        userService.save(user);
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("user", new User());
    }
}
