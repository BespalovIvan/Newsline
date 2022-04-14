package com.example.Newsline.service;

import com.example.Newsline.domain.Role;
import com.example.Newsline.domain.User;
import com.example.Newsline.repos.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public  UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public void createUser(User user, Model model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            throw new IllegalArgumentException("User exist!");
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }
    }

