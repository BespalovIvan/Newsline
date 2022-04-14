package com.example.Newsline.controller;

import com.example.Newsline.domain.Role;
import com.example.Newsline.domain.User;
import com.example.Newsline.repos.UserRepo;
import com.example.Newsline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        try{
            userService.createUser(user,model);
        }
        catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            return "/registration";
        }
    return "redirect:/login";
    }
}