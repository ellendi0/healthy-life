package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;

public interface IFoodToRecipeService {
    FoodToRecipe createUpdateFoodToRecipe(FoodToRecipe foodToRecipe);
    FoodToRecipe getFoodByRecipeIdAndFoodId(Long recipeId, Long foodId);
    Recipe updateFoodToRecipe(Long recipeId, Long foodId, Double weight);
    void deleteByRecipeIdAndFoodId(Long recipeId, Long foodId);
}
