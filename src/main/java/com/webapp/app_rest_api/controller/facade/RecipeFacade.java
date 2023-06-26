package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.FoodToRecipe;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.mapper.RecipeMapper;
import com.webapp.app_rest_api.service.impl.RecipeService;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class RecipeFacade {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeFacade(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    public RecipeDto createRecipe(Recipe recipe) {
        return recipeMapper.mapToDto(recipeService.createUpdateRecipe(recipe));
    }

    public RecipeDto getRecipe(Long id) {
        return recipeMapper.mapToDto(recipeService.getRecipe(id));
    }

    public List<RecipeDto> getAllRecipe() {
        return recipeService.getAllRecipe().stream()
                .map(recipeMapper::mapToDto)
                .toList();
    }

    public RecipeDto updateRecipe(Long id, Recipe recipe) {
        return recipeMapper.mapToDto(recipeService.updateRecipe(id, recipe));
    }

    public RecipeDto addFoodToRecipe(Long recipeId, Long foodId, double weight) {
        recipeService.addFoodToRecipe(recipeId, foodId, weight);
        countNutritiousFromFoodList(recipeId);
        return recipeMapper.mapToDto(recipeService.getRecipe(recipeId));
    }

    public RecipeDto updateFoodInRecipe(Long recipeId, Long foodId, Double weight) {
        recipeService.updateFoodInRecipe(recipeId, foodId, weight);
        countNutritiousFromFoodList(recipeId);
        return recipeMapper.mapToDto(recipeService.getRecipe(recipeId));
    }

    public RecipeDto deleteRecipe(Long id) {
        RecipeDto recipeDto = recipeMapper.mapToDto(recipeService.getRecipe(id));
        recipeService.deleteRecipe(id);
        return recipeDto;
    }

    public void deleteAllRecipe() {
        recipeService.deleteAllRecipe();
    }

    public RecipeDto deleteFoodFromRecipe(Long recipeId, Long foodId) {
        RecipeDto recipeDto = recipeMapper.mapToDto(recipeService.getRecipe(recipeId));
        recipeService.deleteFoodFromRecipe(recipeId, foodId);
        countNutritiousFromFoodList(recipeId);
        return recipeDto;
    }

    public RecipeDto countNutritiousFromFoodList(Long recipeId) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double fiber = 0;
        double sugar = 0;
        double weight = 0;

        Recipe recipe = recipeService.getRecipe(recipeId);
        RecipeDto recipeDto = recipeMapper.mapToDto(recipe);

        if(recipeDto.getFood() != null) {
            for (FoodDto foodDto : recipeDto.getFood()) {
                calories += foodDto.getNumberOfCalories();
                proteins += foodDto.getNumberOfProtein();
                fats += foodDto.getNumberOfFat();
                carbohydrates += foodDto.getNumberOfCarbohydrate();
                weight += foodDto.getWeight();
                fiber += foodDto.getNumberOfFiber();
                sugar += foodDto.getNumberOfSugar();
            }
        }

        recipe.setNumberOfCalories(calories);
        recipe.setNumberOfProtein(proteins);
        recipe.setNumberOfFat(fats);
        recipe.setNumberOfCarbohydrate(carbohydrates);
        recipe.setWeight(weight);
        recipe.setNumberOfFiber(fiber);
        recipe.setNumberOfSugar(sugar);

        recipeService.createUpdateRecipe(recipe);
        return recipeDto;
    }

    public RecipeDto getRecipeWithGivenWeight(Long recipeId, Double weight) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        RecipeDto recipeDto = recipeMapper.mapToDto(recipe);
        recipeDto.setName(recipe.getName());
        recipeDto.setWeight(weight);
        recipeDto.setNumberOfCalories(DoubleRounder.round(recipeDto.getNumberOfCalories() * weight / 100, 3));
        recipeDto.setNumberOfFat(DoubleRounder.round(recipeDto.getNumberOfFat() * weight / 100, 3));
        recipeDto.setNumberOfCarbohydrate(DoubleRounder.round(recipeDto.getNumberOfCarbohydrate() * weight / 100, 3));
        recipeDto.setNumberOfProtein(DoubleRounder.round(recipeDto.getNumberOfProtein() * weight / 100, 3));
        recipeDto.setNumberOfSugar(DoubleRounder.round(recipeDto.getNumberOfSugar() * weight / 100, 3));
        recipeDto.setNumberOfFiber(DoubleRounder.round(recipeDto.getNumberOfFiber() * weight / 100, 3));
        return recipeDto;
    }
}
