package com.example.Newsline.controller;
import com.example.Newsline.domain.User;
import com.example.Newsline.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String UserList(Model model){
        userService.userList(model);
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(Model model,@PathVariable User user){
        userService.editUserList(model,user);
        return "UserEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user
    ){
        userService.saveUser(username,form,user);
        return "redirect:/user";
    }
}
