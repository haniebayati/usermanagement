package com.example.user_management.controller;

import java.util.List;

import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_management.service.KeycloakUserService;

@RestController
@RequestMapping("/api/keycloak")
public class KeycloakUserController {

    private final KeycloakUserService userService;

    public KeycloakUserController(KeycloakUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/users")
    public ResponseEntity<List<UserRepresentation>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/roles")
    public ResponseEntity<List<RoleRepresentation>> getRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }
}

