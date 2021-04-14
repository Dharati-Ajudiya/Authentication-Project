package com.example.jwtdemo.dao;

import com.example.jwtdemo.Entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserDao extends JpaRepository<CustomUser, Integer> {
    CustomUser findByUsername(String username);
}
