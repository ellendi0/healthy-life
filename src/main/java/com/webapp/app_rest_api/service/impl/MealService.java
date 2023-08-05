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
    private final DayDietRepository dayDietRepository;
    private final FoodService foodService;
    private final RecipeService recipeService;
    private final FoodToMealService foodToMealService;
    private final RecipeToMealService recipeToMealService;

    public MealService(MealRepository mealRepository,
                       DayDietRepository dayDietRepository,
                       FoodService foodService,
                       RecipeService recipeService,
                       FoodToMealService foodToMealService,
                       RecipeToMealService recipeToMealService) {
        this.mealRepository = mealRepository;
        this.dayDietRepository = dayDietRepository;
        this.foodService = foodService;
        this.recipeService = recipeService;
        this.foodToMealService = foodToMealService;
        this.recipeToMealService = recipeToMealService;
    }

    @Override
    public Meal createSaveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal getMealByUserAndMealId(PersonalInfo personalInfo, Long mealId) {
        return mealRepository.findMealByPersonalInfo(personalInfo.getId(), mealId).orElseThrow(()
                -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealId)));
    }

    @Override
    public Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(id)));
    }

    @Override
    public Meal createSaveMeal(DayDiet dayDiet, TypeOfMeal typeOfMeal) {
        Meal meal = new Meal();
        meal.setDayDiet(dayDiet);
        meal.setTypeOfMeal(typeOfMeal);
        return mealRepository.save(meal);
    }

    @Override
    public List<Meal> getAllMealsByDayDiet(PersonalInfo personalInfo, Long dayDietId) {
        DayDiet dayDiet = dayDietRepository.findDayDietByIdAndPersonalInfo_Id(dayDietId, personalInfo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("DayDiet", "id", String.valueOf(dayDietId)));

        return mealRepository.findAllByDayDiet(dayDiet);
    }

    @Override
    @Transactional
    public Meal addFoodToMeal(PersonalInfo personalInfo, Long mealId, Long foodId, Double weight) {
        Meal meal = getMealByUserAndMealId(personalInfo, mealId);
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
        return createSaveMeal(meal);
    }

    @Override
    @Transactional
    public Meal addRecipeToMeal(PersonalInfo personalInfo, Long mealId, Long recipeId, Double weight) {
        Meal meal = getMealByUserAndMealId(personalInfo, mealId);
        RecipeToMeal recipeToMeal = recipeToMealService.getRecipeByMealIdAndRecipeId(mealId, recipeId);

        if (Objects.isNull(recipeToMeal)) {
            Recipe recipe = recipeService.getRecipeById(personalInfo, recipeId);
            recipeToMeal = new RecipeToMeal(recipe, meal, weight);
            meal.getRecipe().add(recipeToMeal);
            recipe.getMeal().add(recipeToMeal);
        } else {
            recipeToMeal.setWeight(recipeToMeal.getWeight() + weight);
        }

        recipeToMealService.createSaveRecipeToMeal(recipeToMeal);
        return createSaveMeal(meal);
    }

    @Override
    public Meal updateFoodInMeal(PersonalInfo personalInfo, Long mealId, Long foodId, Double weight) {
        return foodToMealService.updateFoodToMeal(getMealByUserAndMealId(personalInfo, mealId).getId(), foodId, weight);
    }

    @Override
    public Meal updateRecipeInMeal(PersonalInfo personalInfo, Long mealId, Long recipeId, Double weight) {
        return recipeToMealService.updateRecipeToMeal(getMealByUserAndMealId(
                personalInfo, mealId).getId(), recipeId, weight
        );
    }

    @Override
    @Transactional
    public void deleteFoodFromMeal(PersonalInfo personalInfo, Long mealId, Long foodId) {
        foodToMealService.deleteFoodByMealIdAndFoodId(
                getMealByUserAndMealId(personalInfo, mealId).getId(), foodId
        );
    }

    @Override
    @Transactional
    public void deleteRecipeFromMeal(PersonalInfo personalInfo, Long mealId, Long recipeId) {
        recipeToMealService.deleteRecipeByMealIdAndRecipeId(
                getMealByUserAndMealId(personalInfo, mealId).getId(), recipeId
        );
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
