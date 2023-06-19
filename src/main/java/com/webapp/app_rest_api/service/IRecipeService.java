package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.Recipe;

import java.util.List;

public interface IRecipeService {
    RecipeDto getRecipeById(long id);
    List<RecipeDto> getAllRecipe();
    Recipe createRecipe(Recipe recipe);
    RecipeDto addFoodToRecipe(long recipeId, long foodId, double weight);
    RecipeDto updateRecipe(long id, RecipeDto recipeDto);
    RecipeDto updateFoodInRecipe(long recipeId, long foodId, double weight);
    void deleteRecipe(long id);
    void deleteFoodFromRecipe(long recipeId, long foodId);
    void countNutritiousFromFoodList(RecipeDto recipeDto);
    RecipeDto getRecipeWithGivenWeight(long recipeId, double weight);
}
