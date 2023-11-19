package com.example.finalproject.securityJwt.dao;

import com.example.finalproject.securityJwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
}
