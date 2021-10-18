package com.example.demospringboot.service;

import com.example.demospringboot.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByName(String name);
    void save(User user);
}
