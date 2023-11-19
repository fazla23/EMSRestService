package com.example.finalproject.securityJwt.controller;

import com.example.finalproject.securityJwt.model.Role;
import com.example.finalproject.securityJwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService service;
    @PostMapping("/create")
    public Role createRole(@RequestBody Role role){
        return service.createRole(role);
    }

}
