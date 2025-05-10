package com.example.user_management.controller;

import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_user') or hasRole('ROLE_admin')")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @PostMapping
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existingUser = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        
        return repository.save(existingUser);
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
