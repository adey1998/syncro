package com.syncro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syncro.model.User;
import com.syncro.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userService.createUser(user);
    }

    //Get a user by ID
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    //Get all users
    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
