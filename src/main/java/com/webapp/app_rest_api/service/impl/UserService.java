package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.BearerToken;
import com.webapp.app_rest_api.dto.LoginDto;
import com.webapp.app_rest_api.dto.RegisterDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.Role;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.model.mapper.UserMapper;
import com.webapp.app_rest_api.repository.UserRepository;
import com.webapp.app_rest_api.security.JwtUtilities;
import com.webapp.app_rest_api.service.IUserService;
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
public class UserService implements IUserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PersonalInfoService personalInfoService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;
    private final UserMapper mapper;

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken", HttpStatus.SEE_OTHER);
        } else {
            User user = mapper.map(registerDto);
            PersonalInfo personalInfo = new PersonalInfo();

            Role userRole = roleService.getRoleByName("ROLE_USER");

            personalInfoService.createPersonalInfo(personalInfo);

            user.getRoles().add(userRole);
            userRole.getUsers().add(user);

            user.setPersonalInfo(personalInfoService.createPersonalInfo(personalInfo));
            personalInfo.setUser(user);

            userRepository.save(user);

            String token = jwtUtilities.generateToken(registerDto.getEmail(), Collections.singletonList(userRole.getName()));
            return new ResponseEntity<>(new BearerToken(token, "Bearer "), HttpStatus.OK);
        }
    }

    @Override
    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.
                findByEmail(authentication.getName()).orElseThrow(()
                        -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getName()));
        return jwtUtilities.generateToken(user.getUsername(), rolesNames);
    }
}
