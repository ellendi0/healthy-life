package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;
import com.webapp.app_rest_api.repository.RecipeToMealRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeToMealService {
    private RecipeToMealRepository recipeToMealRepository;

    public RecipeToMealService(RecipeToMealRepository recipeToMealRepository) {
        this.recipeToMealRepository = recipeToMealRepository;
    }

    public RecipeToMeal createUpdateRecipeToMeal(RecipeToMeal recipeToMeal) {
        return recipeToMealRepository.save(recipeToMeal);
    }

    public RecipeToMeal getRecipeByMealIdAndRecipeId(Long mealId, Long recipeId) {
        return recipeToMealRepository.getRecipeToMealByMealIdAndRecipeId(mealId, recipeId).orElseThrow(()
                -> new ResourceNotFoundException(
                        "Recipe", "id", String.valueOf(recipeId), "Meal", "id", String.valueOf(mealId)));
    }

    public RecipeToMeal updateRecipeToMeal(Long mealId, Long recipeId, Double weight) {
        RecipeToMeal recipeToMeal = getRecipeByMealIdAndRecipeId(mealId, recipeId);
        recipeToMeal.setWeight(weight);
        return recipeToMealRepository.save(recipeToMeal);
    }

    public void deleteByMealIdAndRecipeId(long mealId, long recipeId) {
        if(!recipeToMealRepository.existsByMealIdAndRecipeId(mealId, recipeId)) {
            throw new ResourceNotFoundException(
                    "Recipe", "id", String.valueOf(recipeId), "Meal", "id", String.valueOf(mealId));
        }

        recipeToMealRepository.deleteRecipeToMealByMealIdAndRecipeId(mealId, recipeId);
    }
}
