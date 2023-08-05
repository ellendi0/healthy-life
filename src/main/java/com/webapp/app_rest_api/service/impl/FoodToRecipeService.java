package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.repository.FoodToRecipeRepository;
import com.webapp.app_rest_api.service.IFoodToRecipeService;
import org.springframework.stereotype.Service;

@Service
public class FoodToRecipeService implements IFoodToRecipeService {
    private final FoodToRecipeRepository foodToRecipeRepository;

    public FoodToRecipeService(FoodToRecipeRepository foodToRecipeRepository) {
        this.foodToRecipeRepository = foodToRecipeRepository;
    }

    @Override
    public FoodToRecipe createUpdateFoodToRecipe(FoodToRecipe foodToRecipe) {
        return foodToRecipeRepository.save(foodToRecipe);
    }

    @Override
    public FoodToRecipe getFoodByRecipeIdAndFoodId(Long recipeId, Long foodId) {
        return foodToRecipeRepository.getFoodToRecipeByRecipeIdAndFoodId(recipeId, foodId).orElse(null);
    }

    @Override
    public Recipe updateFoodToRecipe(Long recipeId, Long foodId, Double weight) {
        FoodToRecipe foodToRecipe = foodToRecipeRepository.getFoodToRecipeByRecipeIdAndFoodId(recipeId, foodId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Food", "id", String.valueOf(foodId), "Recipe", "id", String.valueOf(recipeId)));
        foodToRecipe.setWeight(weight);
        return foodToRecipeRepository.save(foodToRecipe).getRecipe();
    }

    @Override
    public void deleteByRecipeIdAndFoodId(Long recipeId, Long foodId) {
        if (!foodToRecipeRepository.existsByRecipe_IdAndFood_Id(recipeId, foodId)) {
            throw new ResourceNotFoundException(
                    "Food", "id", String.valueOf(foodId), "Recipe", "id", String.valueOf(recipeId));
        }

        foodToRecipeRepository.deleteFoodToRecipeByRecipe_IdAndFood_Id(recipeId, foodId);
    }

}
