package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.FoodRecipe;

import java.util.List;

public interface IFoodRecipeService {
    FoodRecipe addFoodToRecipe(long idRecipe, long idFood, double weight);
    List<Food> getAllFoodFromRecipe(long id);
    void deleteFoodFromRecipe(long id, long foodId);
    FoodRecipe changeFoodWeight(long idRecipe, long idFood, double weight);
}
