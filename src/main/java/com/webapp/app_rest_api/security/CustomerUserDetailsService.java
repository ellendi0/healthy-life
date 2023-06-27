package com.webapp.app_rest_api.security;

import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found !"));
        return user;
    }
}
