package com.example.finalproject.securityJwt.service;

import com.example.finalproject.securityJwt.dao.RoleDao;
import com.example.finalproject.securityJwt.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao dao;
    public Role createRole(Role role){
        return dao.save(role);
    }
}
