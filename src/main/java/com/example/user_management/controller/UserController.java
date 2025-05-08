package com.example.user_management.controller;

import com.example.user_management.model.User;
import com.example.user_management.repository.UserRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAll() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return repository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin() {
        return "سلام ادمین جان! 🎉 شما دسترسی دارید.";
    }
    
    @GetMapping("/me")
    public String whoAmI(Authentication authentication) {
        return authentication.getAuthorities().toString();
    }

}
