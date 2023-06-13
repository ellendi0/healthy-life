package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.FoodRecipe;
import com.webapp.app_rest_api.model.Meal;
import com.webapp.app_rest_api.model.Recipe;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.repository.MealRepository;
import com.webapp.app_rest_api.repository.RecipeRepository;
import com.webapp.app_rest_api.service.IFoodService;
import com.webapp.app_rest_api.service.IMealService;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MealService implements IMealService {

    private FoodRepository foodRepository;
    private RecipeRepository recipeRepository;
    private MealRepository mealRepository;
    private IFoodService iFoodService;
    private IRecipeService iRecipeService;

    public MealService(FoodRepository foodRepository, RecipeRepository recipeRepository, MealRepository mealRepository, IFoodService iFoodService, IRecipeService iRecipeService) {
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
        this.mealRepository = mealRepository;
        this.iFoodService = iFoodService;
        this.iRecipeService = iRecipeService;
    }

    @Override
    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal addFoodToMeal(long mealId, long foodId, double weight) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(()
                -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealId)));
        //Food food = iFoodService.getFoodWithGivenWeight(foodId, weight);
        Food existingFood = meal.getFood().stream().filter(f -> f.getId() == foodId).findFirst().orElse(null);

        if (Objects.isNull(existingFood)) {
            meal.addFood(iFoodService.getFoodWithGivenWeight(foodId, weight));
            mealRepository.save(meal);
        } else {
            double old_weight = existingFood.getWeight();
            meal.removeFood(existingFood);
            meal.addFood(iFoodService.getFoodWithGivenWeight(foodId, weight + old_weight));
            mealRepository.save(meal);
        }
        countNutritiousFromFoodList(meal);
        return meal;
    }

    @Override
    public Meal addRecipeToMeal(long mealId, long recipeId) {
        return null;
    }

    @Override
    public Meal updateFoodInMeal(long mealId, long foodId, double weight) {
        return null;
    }

    @Override
    public Meal updateRecipeInMeal(long mealId, long recipeId, double weight) {
        return null;
    }

    @Override
    public void deleteFoodFromMeal(long mealId, long foodId) {

    }

    @Override
    public void deleteRecipeFromMeal(long mealId, long recipeId) {

    }

    @Override
    public void deleteMeal(long mealId) {

    }

    @Override
    public void deleteAllMeals() {

    }

    @Override
    public void countNutritiousFromFoodList(Meal meal) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;

        for (Food f : meal.getFood()) {
            Food food = iFoodService.getFoodWithGivenWeight(meal.getId(), f.getWeight());
            calories += food.getNumberOfCalories();
            proteins += food.getNumberOfProtein();
            fats += food.getNumberOfFat();
            carbohydrates += food.getNumberOfCarbohydrate();
            weight += food.getWeight();
        }

        meal.setNumberOfCalories(calories);
        meal.setNumberOfProtein(proteins);
        meal.setNumberOfFat(fats);
        meal.setNumberOfCarbohydrate(carbohydrates);
        meal.setWeight(weight);
        mealRepository.save(meal);
    }


}
