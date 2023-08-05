package com.webapp.app_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietDto {
    private Double numberOfDailyCalories;
    private Double numberOfProtein;
    private Double numberOfFat;
    private Double numberOfCarbohydrate;
    private Double numberOfSugar;
    private Double numberOfFiber;
}
