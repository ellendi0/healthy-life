package com.webapp.app_rest_api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayDietDto {
    private LocalDate date;
    private Double totalNumberOfDailyCalories;
    private Double totalNumberOfProtein;
    private Double totalNumberOfFat;
    private Double totalNumberOfCarbohydrate;
    private Double totalNumberOfSugar;
    private Double totalNumberOfFiber;
    private List<MealDto> meals = new ArrayList<>();
}
