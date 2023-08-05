package com.webapp.app_rest_api.dto;

import com.webapp.app_rest_api.model.enums.RecipeAccess;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    private Double numberOfCalories;
    private Double weight;
    private Double numberOfProtein;
    private Double numberOfFat;
    private Double numberOfCarbohydrate;
    private Double numberOfSugar;
    private Double numberOfFiber;

    @NotNull(message = "Recipe access cannot be empty")
    private RecipeAccess recipeAccess;

    private Set<FoodDto> food = new HashSet<>();
}
