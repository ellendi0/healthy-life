package com.webapp.app_rest_api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.webapp.app_rest_api.model.enums.Activity;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.enums.Goal;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoDto {

    @NotEmpty(message = "Weight cannot be empty")
    @Size(min = 1, max = 3, message = "Weight must be between 1 and 3 characters")
    private Double weight;

    @NotEmpty(message = "Height cannot be empty")
    @Size(min = 1, max = 3, message = "Height must be between 1 and 3 characters")
    private Double height;

    @NotEmpty(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Gender cannot be empty")
    private Gender gender;

    @NotEmpty(message = "Goal cannot be empty")
    private Goal goal;

    @NotEmpty(message = "Activity cannot be empty")
    private Activity activity;
}
