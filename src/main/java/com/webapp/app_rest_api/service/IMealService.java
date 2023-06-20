package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.model.Meal;

import java.util.List;

public interface IMealService {

    Meal createMeal(Meal meal);
    MealDto getMeal(long id);
    List<MealDto> getAllMeals();
    MealDto addFoodToMeal(long mealId, long foodId, double weight);
    MealDto addRecipeToMeal(long mealId, long recipeId, double weight);
    MealDto updateFoodInMeal(long mealId, long foodId, double weight);
    MealDto updateRecipeInMeal(long mealId, long recipeId, double weight);
    void deleteFoodFromMeal(long mealId, long foodId);
    void deleteRecipeFromMeal(long mealId, long recipeId);
    void deleteMeal(long mealId);
    void deleteAllMeals();
    void countNutritiousFromFoodList(long mealId);
}
