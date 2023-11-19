package com.example.finalproject.securityJwt.controller;

import com.example.finalproject.securityJwt.model.User;
import com.example.finalproject.securityJwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/registerNewUser")
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('USER')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

}
