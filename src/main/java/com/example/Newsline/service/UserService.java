package com.example.Newsline.service;

import com.example.Newsline.domain.User;
import org.springframework.ui.Model;

public interface UserService {
    void createUser(User user, Model model);

}
