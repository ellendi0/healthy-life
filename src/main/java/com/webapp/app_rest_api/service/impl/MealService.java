package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.*;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import com.webapp.app_rest_api.repository.*;
import com.webapp.app_rest_api.service.IMealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MealService implements IMealService {
    private final MealRepository mealRepository;
    private final FoodService foodService;
    private final RecipeService recipeService;
    private final FoodToMealService foodToMealService;
    private final RecipeToMealService recipeToMealService;

    public MealService(MealRepository mealRepository,
                       FoodService foodService,
                       RecipeService recipeService,
                       FoodToMealService foodToMealService,
                       RecipeToMealService recipeToMealService) {
        this.mealRepository = mealRepository;
        this.foodService = foodService;
        this.recipeService = recipeService;
        this.foodToMealService = foodToMealService;
        this.recipeToMealService = recipeToMealService;
    }

    @Override
    public Meal createUpdateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal getMeal(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(id)));
    }

    @Override
    public Meal createMeal(TypeOfMeal typeOfMeal) {
        Meal meal = new Meal();
        meal.setTypeOfMeal(typeOfMeal);
        return createUpdateMeal(meal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    @Transactional
    public Meal addFoodToMeal(Long mealId, Long foodId, Double weight) {
        Meal meal = getMeal(mealId);
        FoodToMeal foodToMeal = foodToMealService.getFoodToMealById(mealId, foodId);

        if (Objects.isNull(foodToMeal)) {
            Food food = foodService.getFoodById(foodId);
            foodToMeal = new FoodToMeal(food, meal, weight);
            meal.getFood().add(foodToMeal);
            food.getMeal().add(foodToMeal);
        } else {
            foodToMeal.setWeight(foodToMeal.getWeight() + weight);
        }

        foodToMealService.createUpdateFoodToMeal(foodToMeal);
        createUpdateMeal(meal);
        return meal;
    }

    @Override
    @Transactional
    public Meal addRecipeToMeal(Long mealId, Long recipeId, Double weight) {
        Meal meal = getMeal(mealId);

        RecipeToMeal recipeToMeal = recipeToMealService.getRecipeByMealIdAndRecipeId(mealId, recipeId);

        if (Objects.isNull(recipeToMeal)) {
            Recipe recipe = recipeService.getRecipe(recipeId);
            recipeToMeal = new RecipeToMeal(recipe, meal, weight);
            meal.getRecipe().add(recipeToMeal);
            recipe.getMeal().add(recipeToMeal);
        } else {
            recipeToMeal.setWeight(recipeToMeal.getWeight() + weight);
        }
        recipeToMealService.createUpdateRecipeToMeal(recipeToMeal);
        createUpdateMeal(meal);
        return meal;
    }

    @Override
    public Meal updateFoodInMeal(Long mealId, Long foodId, Double weight) {
        foodToMealService.updateFoodToMeal(mealId, foodId, weight);
        return getMeal(mealId);
    }

    @Override
    public Meal updateRecipeInMeal(Long mealId, Long recipeId, Double weight) {
        recipeToMealService.updateRecipeToMeal(mealId, recipeId, weight);
        return getMeal(mealId);
    }

    @Override
    @Transactional
    public void deleteFoodFromMeal(Long mealId, Long foodId) {
        foodToMealService.deleteFoodToMeal(mealId, foodId);
    }

    @Override
    @Transactional
    public void deleteRecipeFromMeal(Long mealId, Long recipeId) {
        recipeToMealService.deleteByMealIdAndRecipeId(mealId, recipeId);
    }

    @Override
    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }

    @Override
    public void deleteAllMeals() {
        mealRepository.deleteAll();
    }
}
