package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;

public interface IFoodToMealService {
    FoodToMeal getFoodToMealById(Long mealId, Long foodId);

    FoodToMeal createUpdateFoodToMeal(FoodToMeal foodToMeal);

    FoodToMeal updateFoodToMeal(Long mealId, Long foodId, Double weight);

    void deleteFoodToMeal(Long mealId, Long foodId);
}
