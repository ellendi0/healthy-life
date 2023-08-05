package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.model.entities.Recipe;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeMapper {
    public Recipe mapToEntity(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeDto.getName());
        recipe.setRecipeAccess(recipeDto.getRecipeAccess());
        return recipe;
    }

    public RecipeDto mapToDto(Recipe recipe){
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName(recipe.getName());
        recipeDto.setNumberOfCalories(recipe.getNumberOfCalories());
        recipeDto.setNumberOfProtein(recipe.getNumberOfProtein());
        recipeDto.setNumberOfFat(recipe.getNumberOfFat());
        recipeDto.setNumberOfCarbohydrate(recipe.getNumberOfCarbohydrate());
        recipeDto.setNumberOfSugar(recipe.getNumberOfSugar());
        recipeDto.setNumberOfFiber(recipe.getNumberOfFiber());
        recipeDto.setWeight(recipe.getWeight());
        recipeDto.setRecipeAccess(recipe.getRecipeAccess());
        recipeDto.getFood().addAll(mapFoodToFoodDTO(recipe.getFood()));
        return recipeDto;
    }

    public Set<FoodDto> mapFoodToFoodDTO(Set<FoodToRecipe> foodToMeals) {
        Set<FoodDto> foodDTOs = new HashSet<>();
        for (FoodToRecipe foodToRecipe : foodToMeals) {
            Food food = foodToRecipe.getFood();
            FoodDto foodDTO = new FoodDto();
            foodDTO.setName(food.getName());
            foodDTO.setWeight(foodToRecipe.getWeight());
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

    public RecipeDto mapToDto(Recipe recipe, Double weight){
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setName(recipe.getName());
        recipeDto.setNumberOfCalories(DoubleRounder.round(recipe.getNumberOfCalories() * weight / 100, 3));
        recipeDto.setNumberOfProtein(DoubleRounder.round(recipe.getNumberOfProtein() * weight / 100, 3));
        recipeDto.setNumberOfFat(DoubleRounder.round(recipe.getNumberOfFat() * weight / 100, 3));
        recipeDto.setNumberOfCarbohydrate(DoubleRounder.round(recipe.getNumberOfCarbohydrate() * weight / 100, 3));
        recipeDto.setNumberOfSugar(DoubleRounder.round(recipe.getNumberOfSugar() * weight / 100, 3));
        recipeDto.setNumberOfFiber(DoubleRounder.round(recipe.getNumberOfFiber() * weight / 100, 3));
        recipeDto.setWeight(weight);
        recipeDto.setRecipeAccess(recipe.getRecipeAccess());
        recipeDto.getFood().addAll(mapFoodToFoodDTO(recipe.getFood()));
        return recipeDto;
    }
}
