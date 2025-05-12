package com.example.user_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
    	this.repository = repository;
    }

    public User updateUser(Long id, User user) {
        User existingUser = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return repository.save(existingUser);
    }
}

