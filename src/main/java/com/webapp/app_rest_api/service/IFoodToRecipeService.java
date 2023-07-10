package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;

public interface IFoodToRecipeService {
    FoodToRecipe createUpdateFoodToRecipe(FoodToRecipe foodToRecipe);

    FoodToRecipe getFoodByRecipeIdAndFoodId(Long recipeId, Long foodId);

    FoodToRecipe updateFoodToRecipe(Long recipeId, Long foodId, Double weight);

    void deleteByRecipeIdAndFoodId(Long recipeId, Long foodId);
}
