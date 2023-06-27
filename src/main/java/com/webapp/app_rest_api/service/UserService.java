package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.dto.BearerToken;
import com.webapp.app_rest_api.dto.LoginDto;
import com.webapp.app_rest_api.dto.RegisterDto;
import com.webapp.app_rest_api.model.entities.Role;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.repository.RoleRepository;
import com.webapp.app_rest_api.repository.UserRepository;
import com.webapp.app_rest_api.security.JwtUtilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;

    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER);
        } else {
            User user = new User();
            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName("ROLE_USER").get();
            roles.add(userRole);
            user.setRoles(roles);
            userRepository.save(user);
            String token = jwtUtilities.generateToken(registerDto.getEmail(), Collections.singletonList(userRole.getName()));
            return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);

        }
    }

    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getName()));
        String token = jwtUtilities.generateToken(user.getUsername(), rolesNames);
        return token;
    }

}
