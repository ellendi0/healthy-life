package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;

public interface IRecipeToMealService {
    RecipeToMeal createUpdateRecipeToMeal(RecipeToMeal recipeToMeal);

    RecipeToMeal getRecipeByMealIdAndRecipeId(Long mealId, Long recipeId);

    RecipeToMeal updateRecipeToMeal(Long mealId, Long recipeId, Double weight);

    void deleteByMealIdAndRecipeId(Long mealId, Long recipeId);
}
