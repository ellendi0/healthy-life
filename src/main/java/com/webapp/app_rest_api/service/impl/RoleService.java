package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.entities.Role;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.repository.RoleRepository;
import com.webapp.app_rest_api.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(()
                -> new ResourceNotFoundException("Role", "name", name));
    }

    @Override
    public Role addUser(User user) {
        Role userRole = getRoleByName("ROLE_USER");
        user.getRoles().add(userRole);
        userRole.getUsers().add(user);
        return userRole;
    }
}
