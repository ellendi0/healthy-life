package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public interface IMealService {
    Meal createUpdateMeal(Meal meal);
    Meal getMeal(Long id);
    Meal createMeal(TypeOfMeal typeOfMeal);
    List<Meal> getAllMeals();
    Meal addFoodToMeal(Long mealId, Long foodId, Double weight);
    Meal addRecipeToMeal(Long mealId, Long recipeId, Double weight);
    Meal updateFoodInMeal(Long mealId, Long foodId, Double weight);
    Meal updateRecipeInMeal(Long mealId, Long recipeId, Double weight);
    void deleteFoodFromMeal(Long mealId, Long foodId);
    void deleteRecipeFromMeal(Long mealId, Long recipeId);
    void deleteMeal(Long mealId);
    void deleteAllMeals();
}
