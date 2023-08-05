package com.webapp.app_rest_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}