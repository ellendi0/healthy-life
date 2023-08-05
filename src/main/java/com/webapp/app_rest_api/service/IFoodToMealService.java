package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;

public interface IFoodToMealService {
    FoodToMeal getFoodToMealById(Long mealId, Long foodId);
    Meal createUpdateFoodToMeal(FoodToMeal foodToMeal);
    Meal updateFoodToMeal(Long mealId, Long foodId, Double weight);
    void deleteFoodByMealIdAndFoodId(Long mealId, Long foodId);
}
