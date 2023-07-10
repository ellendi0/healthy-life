package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;
import com.webapp.app_rest_api.repository.RecipeToMealRepository;
import com.webapp.app_rest_api.service.IRecipeToMealService;
import org.springframework.stereotype.Service;

@Service
public class RecipeToMealService implements IRecipeToMealService {
    private final RecipeToMealRepository recipeToMealRepository;

    public RecipeToMealService(RecipeToMealRepository recipeToMealRepository) {
        this.recipeToMealRepository = recipeToMealRepository;
    }

    @Override
    public RecipeToMeal createUpdateRecipeToMeal(RecipeToMeal recipeToMeal) {
        return recipeToMealRepository.save(recipeToMeal);
    }

    @Override
    public RecipeToMeal getRecipeByMealIdAndRecipeId(Long mealId, Long recipeId) {
        return recipeToMealRepository.getRecipeToMealByMealIdAndRecipeId(mealId, recipeId).orElseThrow(()
                -> new ResourceNotFoundException(
                "Recipe", "id", String.valueOf(recipeId), "Meal", "id", String.valueOf(mealId)));
    }

    @Override
    public RecipeToMeal updateRecipeToMeal(Long mealId, Long recipeId, Double weight) {
        RecipeToMeal recipeToMeal = getRecipeByMealIdAndRecipeId(mealId, recipeId);
        recipeToMeal.setWeight(weight);
        return recipeToMealRepository.save(recipeToMeal);
    }

    @Override
    public void deleteByMealIdAndRecipeId(Long mealId, Long recipeId) {
        if (!recipeToMealRepository.existsByMealIdAndRecipeId(mealId, recipeId)) {
            throw new ResourceNotFoundException(
                    "Recipe", "id", String.valueOf(recipeId), "Meal", "id", String.valueOf(mealId));
        }

        recipeToMealRepository.deleteRecipeToMealByMealIdAndRecipeId(mealId, recipeId);
    }
}
