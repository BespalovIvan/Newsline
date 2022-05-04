package com.example.Newsline.service;

import com.example.Newsline.domain.Role;
import com.example.Newsline.domain.User;
import com.example.Newsline.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public void userList(Model model) {
        model.addAttribute("users",userRepo.findAll());
    }

    @Override
    public void editUserList(Model model, User user) {
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
    }

    @Override
    public void saveUser(String username, Map<String, String> form, User user) {

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
               .map(Role::name)
               .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

    }
}

