package com.webapp.app_rest_api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {

    @NotEmpty(message = "Type of meal cannot be empty")
    private TypeOfMeal typeOfMeal;

    @NotEmpty(message = "Name cannot be empty")
    private Double numberOfCalories;

    private Double numberOfProtein;

    private Double numberOfFat;

    private Double numberOfCarbohydrate;

    private Double numberOfSugar;

    private Double numberOfFiber;

    private Double weight;

    private Set<FoodDto> food = new HashSet<>();
    private Set<RecipeDto> recipes = new HashSet<>();
}
