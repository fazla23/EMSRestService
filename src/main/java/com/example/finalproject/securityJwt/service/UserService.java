package com.example.finalproject.securityJwt.service;

import com.example.finalproject.securityJwt.dao.RoleDao;
import com.example.finalproject.securityJwt.dao.UserDao;
import com.example.finalproject.securityJwt.model.Role;
import com.example.finalproject.securityJwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;
    @Autowired
    private RoleDao roleDao;
    public User createUser(User user){
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return dao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
