package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional <Role> findByName(String name);
}
