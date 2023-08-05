package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;

public interface IRecipeToMealService {
    RecipeToMeal createSaveRecipeToMeal(RecipeToMeal recipeToMeal);
    RecipeToMeal getRecipeByMealIdAndRecipeId(Long mealId, Long recipeId);
    Meal updateRecipeToMeal(Long mealId, Long recipeId, Double weight);
    void deleteRecipeByMealIdAndRecipeId(Long mealId, Long recipeId);
}
