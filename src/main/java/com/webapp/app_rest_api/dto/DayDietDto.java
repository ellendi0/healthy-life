package com.webapp.app_rest_api.dto;

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
    private double totalNumberOfDailyCalories;
    private double totalNumberOfProtein;
    private double totalNumberOfFat;
    private double totalNumberOfCarbohydrate;
    private double totalNumberOfSugar;
    private double totalNumberOfFiber;
    private List<MealDto> meals = new ArrayList<>();
}
