package com.webapp.app_rest_api.dto;

import com.webapp.app_rest_api.model.enums.TypeOfFood;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name must be between 2 and 100 characters")
    private String name;

    private TypeOfFood typeOfFood;
    private Double weight;
    private Double numberOfCalories;
    private Double numberOfProtein;
    private Double numberOfFat;
    private Double numberOfCarbohydrate;
    private Double numberOfSugar;
    private Double numberOfFiber;
}
