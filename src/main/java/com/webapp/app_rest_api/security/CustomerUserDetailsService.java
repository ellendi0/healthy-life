package com.webapp.app_rest_api.security;

import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ emailOrUsername));

        return user;
    }
}