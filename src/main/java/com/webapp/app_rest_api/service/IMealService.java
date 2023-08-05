package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;

import java.util.List;

public interface IMealService {
    Meal createSaveMeal(Meal meal);
    Meal getMealById(Long id);
    Meal createSaveMeal(DayDiet dayDiet, TypeOfMeal typeOfMeal);
    Meal getMealByUserAndMealId(PersonalInfo personalInfo, Long mealId);
    List<Meal> getAllMealsByDayDiet(PersonalInfo personalInfo, Long dayDietId);
    Meal addFoodToMeal(PersonalInfo personalInfo, Long mealId, Long foodId, Double weight);
    Meal addRecipeToMeal(PersonalInfo personalInfo, Long mealId, Long recipeId, Double weight);
    Meal updateFoodInMeal(PersonalInfo personalInfo, Long mealId, Long foodId, Double weight);
    Meal updateRecipeInMeal(PersonalInfo personalInfo, Long mealId, Long recipeId, Double weight);
    void deleteFoodFromMeal(PersonalInfo personalInfo, Long mealId, Long foodId);
    void deleteRecipeFromMeal(PersonalInfo personalInfo, Long mealId, Long recipeId);
    void deleteMeal(Long mealId);
    void deleteAllMeals();
}
