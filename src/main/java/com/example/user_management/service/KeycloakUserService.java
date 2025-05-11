package com.example.user_management.service;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class KeycloakUserService {

    private final Keycloak keycloak;
    
    private String realm = "user-management";

    public KeycloakUserService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public List<UserRepresentation> getAllUsers() {
        return keycloak.realm(realm)
                .users()
                .list();
    }

    public List<RoleRepresentation> getAllRoles() {
        return keycloak.realm(realm)
                .roles()
                .list();
    }
}

