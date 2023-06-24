package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.*;
import com.webapp.app_rest_api.model.mapper.FoodMapper;
import com.webapp.app_rest_api.model.mapper.MealMapper;
import com.webapp.app_rest_api.model.mapper.RecipeMapper;
import com.webapp.app_rest_api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MealService {
    private MealRepository mealRepository;
    private FoodService foodService;
    private RecipeService recipeService;
    private FoodToMealService foodToMealService;
    private RecipeToMealService recipeToMealService;
    private MealMapper mealMapper;

    public MealService(MealRepository mealRepository,
                       FoodService foodService,
                       RecipeService recipeService,
                       MealMapper mealMapper,
                       FoodToMealService foodToMealService,
                       RecipeToMealService recipeToMealService) {
        this.mealRepository = mealRepository;
        this.foodService = foodService;
        this.recipeService = recipeService;
        this.mealMapper = mealMapper;
        this.foodToMealService = foodToMealService;
        this.recipeToMealService = recipeToMealService;
    }

    public Meal createUpdateMeal(Meal meal) {
        return mealRepository.save(meal);
    }
    public Meal getMeal(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(id)));
    }

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Transactional
    public Meal addFoodToMeal(long mealId, long foodId, double weight) {
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
        countNutritiousFromFoodList(mealId);
        return meal;
    }

    @Transactional
    public Meal addRecipeToMeal(long mealId, long recipeId, double weight) {
        Meal meal = getMeal(mealId);

        RecipeToMeal recipeToMeal = recipeToMealService.getRecipeByMealIdAndRecipeId(mealId, recipeId);

        if(Objects.isNull(recipeToMeal)){
            Recipe recipe = recipeService.getRecipeById(recipeId);
            recipeToMeal = new RecipeToMeal(recipe, meal, weight);
            meal.getRecipe().add(recipeToMeal);
            recipe.getMeal().add(recipeToMeal);
        }else{
            recipeToMeal.setWeight(recipeToMeal.getWeight() + weight);
        }
        recipeToMealService.createUpdateRecipeToMeal(recipeToMeal);
        createUpdateMeal(meal);
        countNutritiousFromFoodList(mealId);
        return meal;
    }

    public Meal updateFoodInMeal(long mealId, long foodId, double weight) {
        foodToMealService.updateFoodToMeal(mealId, foodId, weight);
        countNutritiousFromFoodList(mealId);
        return getMeal(mealId);
    }

    public Meal updateRecipeInMeal(long mealId, long recipeId, double weight) {
        recipeToMealService.updateRecipeToMeal(mealId, recipeId, weight);
        countNutritiousFromFoodList(mealId);
        return getMeal(mealId);
    }

    @Transactional
    public void deleteFoodFromMeal(long mealId, long foodId) {
        foodToMealService.deleteFoodToMeal(mealId, foodId);
    }

    @Transactional
    public void deleteRecipeFromMeal(long mealId, long recipeId) {
        recipeToMealService.deleteByMealIdAndRecipeId(mealId, recipeId);
    }

    public void deleteMeal(long mealId) {
        mealRepository.deleteById(mealId);
    }

    public void deleteAllMeals() {
        mealRepository.deleteAll();
    }

    public void countNutritiousFromFoodList(long mealId) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;

        Meal meal = getMeal(mealId);

        MealDto mealDto = mealMapper.mapToDto(meal);

        if (mealDto.getFood() != null) {
            for (FoodDto foodDto : mealDto.getFood()) {
                calories += foodDto.getNumberOfCalories();
                proteins += foodDto.getNumberOfProtein();
                fats += foodDto.getNumberOfFat();
                carbohydrates += foodDto.getNumberOfCarbohydrate();
                weight += foodDto.getWeight();
            }
        }

        if (mealDto.getRecipes() != null) {
            for (RecipeDto recipeDto : mealDto.getRecipes()) {
                calories += recipeDto.getNumberOfCalories();
                proteins += recipeDto.getNumberOfProtein();
                fats += recipeDto.getNumberOfFat();
                carbohydrates += recipeDto.getNumberOfCarbohydrate();
                weight += recipeDto.getWeight();
            }
        }
        meal.setNumberOfCalories(calories);
        meal.setNumberOfProtein(proteins);
        meal.setNumberOfFat(fats);
        meal.setNumberOfCarbohydrate(carbohydrates);
        meal.setWeight(weight);

        mealRepository.save(meal);
    }

}
