package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByEmailOrUsername(String email, String username);
    Boolean existsByEmail(String email);
    Optional <User> findByUsername(String email);
    Boolean existsByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsernameOrEmail(String username, String email);
}
