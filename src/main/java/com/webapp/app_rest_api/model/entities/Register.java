package com.webapp.app_rest_api.model.entities;

import com.webapp.app_rest_api.model.enums.Activity;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.enums.Goal;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private String username;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private double weight;
    private int height;
    private Gender gender;
    private Goal goal;
    private Activity activity;
}
