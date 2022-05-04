package com.example.Newsline.service;

import com.example.Newsline.domain.User;
import org.springframework.ui.Model;

import java.util.Map;

public interface UserService {
    void createUser(User user, Model model);
    void userList (Model model);
    void editUserList(Model model, User user);
    void saveUser (String username, Map<String,String> form,User user);

}
