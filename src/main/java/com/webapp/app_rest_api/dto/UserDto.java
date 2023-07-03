package com.webapp.app_rest_api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.webapp.app_rest_api.model.enums.Activity;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.enums.Goal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
    @NotEmpty
    private String username;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    private String token;
}
