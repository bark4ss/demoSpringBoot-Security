package com.example.demospringboot.service.impl;

import com.example.demospringboot.model.Role;
import com.example.demospringboot.model.User;
import com.example.demospringboot.repository.UserRepo;
import com.example.demospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.getById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public void save(User user) {
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
