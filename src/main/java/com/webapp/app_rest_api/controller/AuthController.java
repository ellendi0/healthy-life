package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.LoginDto;
import com.webapp.app_rest_api.dto.RegisterDto;
import com.webapp.app_rest_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto) {
        return userService.authenticate(loginDto);
    }
}