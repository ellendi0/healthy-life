package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.dto.LoginDto;
import com.webapp.app_rest_api.dto.RegisterDto;
import com.webapp.app_rest_api.model.entities.User;

public interface IUserService {
    String register(RegisterDto registerDto);
    User findByEmailOrUsername(String email);
    String authenticate(LoginDto loginDto);
}
