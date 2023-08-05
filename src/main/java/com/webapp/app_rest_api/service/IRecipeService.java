package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.Recipe;

import java.util.List;

public interface IRecipeService {
    Recipe getPublicRecipe(Long id);
    Recipe getRecipe(Long id);
    Recipe getRecipeByIdForUser(PersonalInfo personalInfo, Long id);
    List<Recipe> getAllRecipe(PersonalInfo personalInfo);
    Recipe getRecipeForUser(PersonalInfo personalInfo, Long userId);
    List<Recipe> getAllPublicRecipe();
    Recipe createSaveRecipe(Recipe recipe);
    Recipe createRecipe(PersonalInfo personalInfo, Recipe recipe);
    Recipe addFoodToRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId, Double weight);
    Recipe updateRecipe(PersonalInfo personalInfo, Long recipeId, Recipe recipe);
    Recipe updateFoodInRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId, Double weight);
    void deleteRecipe(PersonalInfo personalInfo, Long id);
    void deleteAllRecipe(PersonalInfo personalInfo);
    void deleteFoodFromRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId);
}
