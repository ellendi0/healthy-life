//package com.webapp.app_rest_api.controller;
//
//import com.webapp.app_rest_api.dto.JWTAuthResponse;
//import com.webapp.app_rest_api.model.entities.Login;
//import com.webapp.app_rest_api.model.entities.Register;
//import com.webapp.app_rest_api.security.UserDetailsService;
//import com.webapp.app_rest_api.service.AuthService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
////фасад
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping(value = {"/login", "/signin"})
//    public JWTAuthResponse login(@RequestBody Login login){
//        String token = authService.login(login);
//
//        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
//        jwtAuthResponse.setAccessToken(token);
//        return jwtAuthResponse;
//    }
//
//    // Build Register REST API
//    @PostMapping(value = {"/register", "/signup"})
//    public String register(@RequestBody Register register){
//        String response = authService.register(register);
//        return response;
//    }
//}
