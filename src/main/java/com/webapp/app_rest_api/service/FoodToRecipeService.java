package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.repository.FoodToRecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodToRecipeService {
    private FoodToRecipeRepository foodToRecipeRepository;

    public FoodToRecipeService(FoodToRecipeRepository foodToRecipeRepository) {
        this.foodToRecipeRepository = foodToRecipeRepository;
    }

    public FoodToRecipe createUpdateFoodToRecipe(FoodToRecipe foodToRecipe) {
        return foodToRecipeRepository.save(foodToRecipe);
    }

    public FoodToRecipe getFoodByRecipeIdAndFoodId(Long recipeId, Long foodId) {
        return foodToRecipeRepository.getFoodToRecipeByRecipeIdAndFoodId(recipeId, foodId).orElse(null);
    }

    public FoodToRecipe updateFoodToRecipe(Long recipeId, Long foodId, Double weight) {
        if(!foodToRecipeRepository.existsByRecipeIdAndFoodId(recipeId, foodId)) {
            throw new ResourceNotFoundException(
                    "Food", "id", String.valueOf(foodId), "Recipe", "id", String.valueOf(recipeId));
        }

        FoodToRecipe foodToRecipe = getFoodByRecipeIdAndFoodId(recipeId, foodId);
        foodToRecipe.setWeight(weight);
        return foodToRecipeRepository.save(foodToRecipe);
    }

    public void deleteByRecipeIdAndFoodId(long recipeId, long foodId) {
        if(!foodToRecipeRepository.existsByRecipeIdAndFoodId(recipeId, foodId)) {
            throw new ResourceNotFoundException(
                    "Food", "id", String.valueOf(foodId), "Recipe", "id", String.valueOf(recipeId));
        }

        foodToRecipeRepository.deleteFoodToRecipeByRecipeIdAndFoodId(recipeId, foodId);
    }

}
