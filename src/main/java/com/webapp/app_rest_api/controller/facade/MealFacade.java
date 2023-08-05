package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.*;
import com.webapp.app_rest_api.model.mapper.MealMapper;
import com.webapp.app_rest_api.service.impl.MealService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MealFacade {

    private final MealService mealService;
    private final MealMapper mealMapper;

    public MealFacade(MealService mealService, MealMapper mealMapper) {
        this.mealService = mealService;
        this.mealMapper = mealMapper;
    }

    public MealDto createUpdateMeal(MealDto mealDto) {
        return mealMapper.mapToDto(mealService.createSaveMeal(mealMapper.mapToEntity(mealDto)));
    }

    public MealDto getMealByUserAndMealId(User user, Long mealId) {
        return mealMapper.mapToDto(mealService.getMealByUserAndMealId(user.getPersonalInfo(), mealId));
    }

    public List<MealDto> getAllMeals(User user, Long dayDietId) {
        return mealService.getAllMealsByDayDiet(user.getPersonalInfo(), dayDietId).stream()
                .map(mealMapper::mapToDto)
                .toList();
    }

    public MealDto addFoodToMeal(User user, Long mealId, Long foodId, Double weight) {
        mealService.addFoodToMeal(user.getPersonalInfo(), mealId, foodId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto addRecipeToMeal(User user, Long mealId, Long recipeId, Double weight) {
        mealService.addRecipeToMeal(user.getPersonalInfo(), mealId, recipeId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto updateFoodInMeal(User user, Long mealId, Long foodId, Double weight) {
        mealService.updateFoodInMeal(user.getPersonalInfo(), mealId, foodId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto updateRecipeInMeal(User user, Long mealId, Long recipeId, Double weight) {
        mealService.updateRecipeInMeal(user.getPersonalInfo(), mealId, recipeId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto deleteFoodFromMeal(User user, Long mealId, Long foodId) {
        mealService.deleteFoodFromMeal(user.getPersonalInfo(), mealId, foodId);
        System.out.println("The food with id " + foodId + " was deleted from meal");
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto deleteRecipeFromMeal(User user, Long mealId, Long recipeId) {
        mealService.deleteRecipeFromMeal(user.getPersonalInfo(), mealId, recipeId);
        System.out.println("The recipe with id " + recipeId + " was deleted from meal");
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto deleteMeal(User user, Long mealId) {
        mealService.deleteMeal(mealId);
        System.out.println("The meal with id " + mealId + " was deleted");
        return mealMapper.mapToDto(mealService.getMealByUserAndMealId(user.getPersonalInfo(), mealId));
    }

    public String deleteAllMeals() {
        mealService.deleteAllMeals();
        return "All meals deleted";
    }

    public MealDto countNutritiousFromFoodList(Long mealId) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;
        double fiber = 0;
        double sugar = 0;

        Meal meal = mealService.getMealById(mealId);
        MealDto mealDto = mealMapper.mapToDto(meal);

        if (mealDto.getFood() != null) {
            for (FoodDto foodDto : mealDto.getFood()) {
                calories += foodDto.getNumberOfCalories();
                proteins += foodDto.getNumberOfProtein();
                fats += foodDto.getNumberOfFat();
                carbohydrates += foodDto.getNumberOfCarbohydrate();
                weight += foodDto.getWeight();
                fiber += foodDto.getNumberOfFiber();
                sugar += foodDto.getNumberOfSugar();
            }
        }

        if (mealDto.getRecipes() != null) {
            for (RecipeDto recipeDto : mealDto.getRecipes()) {
                calories += recipeDto.getNumberOfCalories();
                proteins += recipeDto.getNumberOfProtein();
                fats += recipeDto.getNumberOfFat();
                carbohydrates += recipeDto.getNumberOfCarbohydrate();
                weight += recipeDto.getWeight();
                fiber += recipeDto.getNumberOfFiber();
                sugar += recipeDto.getNumberOfSugar();
            }
        }
        meal.setNumberOfCalories(calories);
        meal.setNumberOfProtein(proteins);
        meal.setNumberOfFat(fats);
        meal.setNumberOfCarbohydrate(carbohydrates);
        meal.setWeight(weight);
        meal.setNumberOfFiber(fiber);
        meal.setNumberOfSugar(sugar);

        return mealMapper.mapToDto(mealService.createSaveMeal(meal));
    }

}
