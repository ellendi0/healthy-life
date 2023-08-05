package com.webapp.app_rest_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto implements Serializable {
    @NotEmpty(message = "Username cannot be empty")
    String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    String password;
}