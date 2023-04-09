package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.FoodRecipe;
import com.webapp.app_rest_api.model.Recipe;

import java.util.List;

public interface IRecipeService {
    Recipe getRecipeById(long id);
    List<Recipe> getAllRecipe();
    Recipe createRecipe(Recipe recipe);
    Recipe updateRecipe(long id, Recipe recipe);
    String deleteRecipe(long id);
    void countNutritiousFromFoodList(Recipe recipe, List<FoodRecipe> foodRecipes);
}
