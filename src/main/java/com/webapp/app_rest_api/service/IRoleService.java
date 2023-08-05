package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.Role;
import com.webapp.app_rest_api.model.entities.User;

public interface IRoleService {
    Role createRole(Role role);
    Role getRoleByName(String name);
    Role addUser(User user);
}
