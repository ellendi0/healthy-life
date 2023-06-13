package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.FoodRecipe;
import com.webapp.app_rest_api.model.Meal;
import com.webapp.app_rest_api.model.Recipe;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;

import java.util.List;
import java.util.Set;

public interface IMealService {

    Meal createMeal(Meal meal);
    Meal addFoodToMeal(long mealId, long foodId, double weight);
    Meal addRecipeToMeal(long mealId, long recipeId);
    Meal updateFoodInMeal(long mealId, long foodId, double weight);
    Meal updateRecipeInMeal(long mealId, long recipeId, double weight);
    void deleteFoodFromMeal(long mealId, long foodId);
    void deleteRecipeFromMeal(long mealId, long recipeId);
    void deleteMeal(long mealId);
    void deleteAllMeals();
    void countNutritiousFromFoodList(Meal meal);
}
