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
        return mealMapper.mapToDto(mealService.createUpdateMeal(mealMapper.mapToEntity(mealDto)));
    }
    public MealDto getMeal(Long id) {
        return mealMapper.mapToDto(mealService.getMeal(id));
    }

    public List<MealDto> getAllMeals() {
        return mealService.getAllMeals().stream()
                .map(mealMapper::mapToDto)
                .toList();
    }

    public MealDto addFoodToMeal(Long mealId, Long foodId, Double weight) {
        mealService.addFoodToMeal(mealId, foodId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto addRecipeToMeal(Long mealId, Long recipeId, Double weight) {
        mealService.addRecipeToMeal(mealId, recipeId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto updateFoodInMeal(Long mealId, Long foodId, Double weight) {
        mealService.updateFoodInMeal(mealId, foodId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto updateRecipeInMeal(Long mealId, Long recipeId, Double weight) {
        mealService.updateRecipeInMeal(mealId, recipeId, weight);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto deleteFoodFromMeal(Long mealId, Long foodId) {
        mealService.deleteFoodFromMeal(mealId, foodId);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto deleteRecipeFromMeal(Long mealId, Long recipeId) {
        mealService.deleteRecipeFromMeal(mealId, recipeId);
        return countNutritiousFromFoodList(mealId);
    }

    public MealDto deleteMeal(Long mealId) {
        MealDto mealDto = mealMapper.mapToDto(mealService.getMeal(mealId));
        mealService.deleteMeal(mealId);
        return mealDto;
    }

    public void deleteAllMeals() {
        mealService.deleteAllMeals();
    }

    public MealDto countNutritiousFromFoodList(Long mealId) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;
        double fiber = 0;
        double sugar = 0;

        Meal meal = mealService.getMeal(mealId);
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

        mealService.createUpdateMeal(meal);
        return mealMapper.mapToDto(mealService.getMeal(mealId));
    }

}
