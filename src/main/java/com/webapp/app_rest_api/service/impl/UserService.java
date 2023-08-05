package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.BearerToken;
import com.webapp.app_rest_api.dto.LoginDto;
import com.webapp.app_rest_api.dto.RegisterDto;
import com.webapp.app_rest_api.exception.BlogAPIException;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = mapper.map(registerDto);
        PersonalInfo personalInfo = new PersonalInfo();

        Role userRole = roleService.getRoleByName("ROLE_USER");

        user.getRoles().add(userRole);
        userRole.getUsers().add(user);

        user.setPersonalInfo(personalInfoService.createPersonalInfo(personalInfo));
        personalInfo.setUser(user);

        userRepository.save(user);

        return "User registered successfully!.";
    }

    @Override
    public User findByEmailOrUsername(String email) {
        return userRepository.findByEmailOrUsername(email, email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ email));
    }

    @Override
    public String authenticate(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtilities.generateToken(authentication);
    }
}
