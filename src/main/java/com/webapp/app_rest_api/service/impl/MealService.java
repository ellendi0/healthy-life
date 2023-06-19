package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.mapper.FoodMapper;
import com.webapp.app_rest_api.mapper.MealMapper;
import com.webapp.app_rest_api.mapper.RecipeMapper;
import com.webapp.app_rest_api.model.*;
import com.webapp.app_rest_api.repository.*;
import com.webapp.app_rest_api.service.IFoodService;
import com.webapp.app_rest_api.service.IMealService;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MealService implements IMealService {
    private MealRepository mealRepository;
    private IFoodService iFoodService;
    private IRecipeService iRecipeService;
    private MealMapper mealMapper;
    private FoodMapper foodMapper;
    private RecipeMapper recipeMapper;
    private FoodRepository foodRepository;
    private FoodToMealRepository foodToMealRepository;
    private RecipeRepository recipeRepository;
    private RecipeToMealRepository recipeToMealRepository;

    public MealService(MealRepository mealRepository, IFoodService iFoodService, IRecipeService iRecipeService, MealMapper mealMapper, FoodMapper foodMapper, RecipeMapper recipeMapper, FoodRepository foodRepository, FoodToMealRepository foodToMealRepository, RecipeRepository recipeRepository, RecipeToMealRepository recipeToMealRepository) {
        this.mealRepository = mealRepository;
        this.iFoodService = iFoodService;
        this.iRecipeService = iRecipeService;
        this.mealMapper = mealMapper;
        this.foodMapper = foodMapper;
        this.recipeMapper = recipeMapper;
        this.foodRepository = foodRepository;
        this.foodToMealRepository = foodToMealRepository;
        this.recipeRepository = recipeRepository;
        this.recipeToMealRepository = recipeToMealRepository;
    }

    @Override
    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public MealDto getMeal(long id) {
        return mealMapper.mapToDto(mealRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(id))));
    }

    @Override
    public List<MealDto> getAllMeals() {
        return mealRepository.findAll().stream()
                .map(mealMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    public MealDto addFoodToMeal(long mealId, long foodId, double weight) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealId)));

        MealDto mealDto = mealMapper.mapToDto(meal);

        FoodToMeal foodToMeal = meal.getFood().stream()
                .filter(f -> f.getFood().getId().equals(foodId))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(foodToMeal)) {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new ResourceNotFoundException("Food", "id", String.valueOf(foodId)));

            FoodToMeal foodToMealNew = new FoodToMeal(food, meal, weight);

            meal.getFood().add(foodToMealNew);
            food.getMeal().add(foodToMealNew);
            foodToMealRepository.save(foodToMealNew);
            mealDto.getFood().add(iFoodService.getFoodWithGivenWeight(foodId, weight));
        } else {
            foodToMeal.setWeight(foodToMeal.getWeight() + weight);
            mealDto.getFood().removeIf(f -> f.getId().equals(foodId));
            mealDto.getFood().add(iFoodService.getFoodWithGivenWeight(foodId, foodToMeal.getWeight()));
        }

        mealRepository.save(meal);
        return mealDto;
    }


    @Override
    @Transactional
    public MealDto addRecipeToMeal(long mealId, long recipeId, double weight) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(()
                -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealId)));

        MealDto mealDto = mealMapper.mapToDto(meal);

        RecipeToMeal recipeToMeal = meal.getRecipe().stream()
                .filter(f -> f.getRecipe().getId().equals(recipeId))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(recipeToMeal)){
            Recipe recipe = recipeRepository.findById(recipeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId)));

            recipeToMeal = new RecipeToMeal(recipe, meal, weight);

            meal.getRecipe().add(recipeToMeal);
            recipe.getMeal().add(recipeToMeal);
            recipeToMealRepository.save(recipeToMeal);
            mealDto.getRecipes().add(iRecipeService.getRecipeWithGivenWeight(recipeId, recipeToMeal.getWeight()));
        }else{
            recipeToMeal.setWeight(recipeToMeal.getWeight() + weight);
            mealDto.getRecipes().removeIf(f -> f.getId().equals(recipeId));
            mealDto.getRecipes().add(iRecipeService.getRecipeWithGivenWeight(recipeId, recipeToMeal.getWeight()));
        }
        mealRepository.save(meal);
        countNutritiousFromFoodList(mealDto);
        return mealDto;
    }

    @Override
    public MealDto updateFoodInMeal(long mealId, long foodId, double weight) {
        MealDto mealDto = getMeal(mealId);
        FoodDto foodDto = mealDto.getFood().stream()
                .filter(f -> f.getId().equals(foodId))
                .findFirst()
                .orElse(null);

        assert foodDto != null;
        mealDto.getFood().remove(foodDto);
        mealDto.getFood().add(iFoodService.getFoodWithGivenWeight(foodId, weight));
        countNutritiousFromFoodList(mealDto);
        return mealDto;
    }

    @Override
    public MealDto updateRecipeInMeal(long mealId, long recipeId, double weight) {
        MealDto mealDto = getMeal(mealId);
        RecipeDto recipeDto = mealDto.getRecipes().stream()
                .filter(r -> r.getId().equals(recipeId))
                .findFirst()
                .orElse(null);

        assert recipeDto != null;
        mealDto.getRecipes().remove(recipeDto);
        mealDto.getRecipes().add(iRecipeService.getRecipeWithGivenWeight(recipeId, weight));
        countNutritiousFromFoodList(mealDto);
        return mealDto;
    }

    @Override
    public void deleteFoodFromMeal(long mealId, long foodId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealId)));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new ResourceNotFoundException("Food", "id", String.valueOf(foodId)));

        meal.getFood().removeIf(f -> f.getFood().getId().equals(foodId));
        food.getMeal().removeIf(f -> f.getMeal().getId().equals(mealId));
        mealRepository.save(meal);
        countNutritiousFromFoodList(mealMapper.mapToDto(meal));
    }

    @Override
    public void deleteRecipeFromMeal(long mealId, long recipeId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealId)));

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId)));

        meal.getRecipe().removeIf(r -> r.getRecipe().getId().equals(recipeId));
        recipe.getMeal().removeIf(r -> r.getMeal().getId().equals(mealId));
        countNutritiousFromFoodList(mealMapper.mapToDto(meal));
        mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(long mealId) {
        mealRepository.deleteById(mealId);
    }


    @Override
    public void deleteAllMeals() {
        mealRepository.deleteAll();
    }

    @Override
    public void countNutritiousFromFoodList(MealDto mealDto) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;

        Meal meal = mealRepository.findById(mealDto.getId()).orElseThrow(()
                -> new ResourceNotFoundException("Meal", "id", String.valueOf(mealDto.getId())));

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
