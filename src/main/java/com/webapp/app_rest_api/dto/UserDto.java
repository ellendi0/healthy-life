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

//    @NotEmpty(message = "Password cannot be empty")
//    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    private String password;

    private String token;

//    @NotEmpty(message = "Date of birth cannot be empty")
//    private String dateOfBirth;
//
//    @NotEmpty(message = "Weight cannot be empty")
//    private String weight;
//
//    @NotEmpty(message = "Height cannot be empty")
//    private String height;
//
//    @NotEmpty(message = "This field cannot be empty")
//    private Gender gender;
//
//    @NotEmpty(message = "This field cannot be empty")
//    private Goal goal;
//
//    @NotEmpty(message = "This field cannot be empty")
//    private Activity activity;
}
