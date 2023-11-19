package com.example.finalproject.securityJwt.dao;

import com.example.finalproject.securityJwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,String> {
}
