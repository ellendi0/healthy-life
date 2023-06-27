package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.*;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;
import com.webapp.app_rest_api.model.entities.connection.RecipeToMeal;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MealMapper {
    public MealDto mapToDto(Meal meal) {
        MealDto mealDto = new MealDto();
        mealDto.setTypeOfMeal(meal.getTypeOfMeal());
        mealDto.setNumberOfCalories(meal.getNumberOfCalories());
        mealDto.setNumberOfProtein(meal.getNumberOfProtein());
        mealDto.setNumberOfFat(meal.getNumberOfFat());
        mealDto.setNumberOfCarbohydrate(meal.getNumberOfCarbohydrate());
        mealDto.setNumberOfSugar(meal.getNumberOfSugar());
        mealDto.setNumberOfFiber(meal.getNumberOfFiber());
        mealDto.setWeight(meal.getWeight());
        mealDto.getFood().addAll(mapFoodToFoodDto(meal.getFood()));
        mealDto.getRecipes().addAll(mapRecipeToRecipeDto(meal.getRecipe()));
        return mealDto;
    }

    public Meal mapToEntity(MealDto mealDto) {
        Meal meal = new Meal();
        meal.setTypeOfMeal(mealDto.getTypeOfMeal());
        meal.setNumberOfCalories(mealDto.getNumberOfCalories());
        meal.setNumberOfProtein(mealDto.getNumberOfProtein());
        meal.setNumberOfFat(mealDto.getNumberOfFat());
        meal.setNumberOfCarbohydrate(mealDto.getNumberOfCarbohydrate());
        meal.setNumberOfSugar(mealDto.getNumberOfSugar());
        meal.setNumberOfFiber(mealDto.getNumberOfFiber());
        meal.setWeight(mealDto.getWeight());
        return meal;
    }

    public Set<FoodDto> mapFoodToFoodDto(Set<FoodToMeal> foodToMeals) {
        Set<FoodDto> foodDTOs = new HashSet<>();
        for (FoodToMeal foodToMeal : foodToMeals) {
            Food food = foodToMeal.getFood();
            FoodDto foodDTO = new FoodDto();
            foodDTO.setName(food.getName());
            foodDTO.setWeight(foodToMeal.getWeight());
            foodDTO.setNumberOfCalories(DoubleRounder
                    .round(food.getNumberOfCalories() * foodDTO.getWeight() / 100, 3));
            foodDTO.setNumberOfFat(DoubleRounder
                    .round(food.getNumberOfFat() * foodDTO.getWeight() / 100, 3));
            foodDTO.setNumberOfCarbohydrate(DoubleRounder
                    .round(food.getNumberOfCarbohydrate() * foodDTO.getWeight() / 100, 3));
            foodDTO.setNumberOfProtein(DoubleRounder
                    .round(food.getNumberOfProtein() * foodDTO.getWeight() / 100, 3));
            foodDTO.setNumberOfSugar(DoubleRounder
                    .round(food.getNumberOfSugar() * foodDTO.getWeight() / 100, 3));
            foodDTO.setNumberOfFiber(DoubleRounder
                    .round(food.getNumberOfFiber() * foodDTO.getWeight() / 100, 3));
            foodDTOs.add(foodDTO);
        }
        return foodDTOs;
    }

    public Set<RecipeDto> mapRecipeToRecipeDto(Set<RecipeToMeal> recipeToMeals) {
        Set<RecipeDto> recipeDtos = new HashSet<>();
        for (RecipeToMeal recipeToMeal : recipeToMeals) {
            Recipe recipe = recipeToMeal.getRecipe();
            RecipeDto recipeDto = new RecipeDto();
            recipeDto.setName(recipe.getName());
            recipeDto.setWeight(recipeToMeal.getWeight());
            recipeDto.setNumberOfCalories(DoubleRounder
                    .round(recipe.getNumberOfCalories() * recipeDto.getWeight() / 100, 3));
            recipeDto.setNumberOfFat(DoubleRounder
                    .round(recipe.getNumberOfFat() * recipeDto.getWeight() / 100, 3));
            recipeDto.setNumberOfCarbohydrate(DoubleRounder
                    .round(recipe.getNumberOfCarbohydrate() * recipeDto.getWeight() / 100, 3));
            recipeDto.setNumberOfProtein(DoubleRounder
                    .round(recipe.getNumberOfProtein() * recipeDto.getWeight() / 100, 3));
            recipeDto.setNumberOfSugar(DoubleRounder
                    .round(recipe.getNumberOfSugar() * recipeDto.getWeight() / 100, 3));
            recipeDto.setNumberOfFiber(DoubleRounder
                    .round(recipe.getNumberOfFiber() * recipeDto.getWeight() / 100, 3));
            recipeDtos.add(recipeDto);
        }
        return recipeDtos;
    }
}
