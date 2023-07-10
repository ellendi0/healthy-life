package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public interface IRecipeService {
    Recipe getRecipe(Long id);

    List<Recipe> getAllRecipe();

    Recipe createUpdateRecipe(Recipe recipe);

    Recipe addFoodToRecipe(Long recipeId, Long foodId, Double weight);

    Recipe updateRecipe(Long recipeId, Recipe recipe);

    Recipe updateFoodInRecipe(Long recipeId, Long foodId, Double weight);

    void deleteRecipe(Long id);

    void deleteAllRecipe();

    void deleteFoodFromRecipe(Long recipeId, Long foodId);
}
