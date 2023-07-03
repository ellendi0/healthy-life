package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.entities.Role;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()
                -> new ResourceNotFoundException("Role", "name", name));
    }

    public Role addUser(User user){
        Role userRole = getRoleByName("ROLE_USER");
        user.getRoles().add(userRole);
        userRole.getUsers().add(user);
        return userRole;
    }
}
