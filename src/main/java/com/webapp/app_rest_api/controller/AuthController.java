package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.BearerToken;
import com.webapp.app_rest_api.dto.LoginDto;
import com.webapp.app_rest_api.dto.RegisterDto;
import com.webapp.app_rest_api.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public String register(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @PostMapping("/login")
    public BearerToken login(@Valid @RequestBody LoginDto loginDto) {
        String token = userService.authenticate(loginDto);

        BearerToken bearerToken = new BearerToken();
        bearerToken.setAccessToken(token);

        return bearerToken;
    }
}