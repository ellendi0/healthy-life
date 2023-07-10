package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.mapper.RecipeMapper;
import com.webapp.app_rest_api.service.impl.RecipeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeFacade {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeFacade(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    public RecipeDto createRecipe(RecipeDto recipeDto) {
        return recipeMapper.mapToDto(recipeService.createUpdateRecipe(recipeMapper.mapToEntity(recipeDto)));
    }

    public RecipeDto getRecipe(Long id) {
        return recipeMapper.mapToDto(recipeService.getRecipe(id));
    }

    public List<RecipeDto> getAllRecipe() {
        return recipeService.getAllRecipe().stream()
                .map(recipeMapper::mapToDto)
                .toList();
    }

    public RecipeDto updateRecipe(Long id, RecipeDto recipeDto) {
        return recipeMapper.mapToDto(recipeService.updateRecipe(id, recipeMapper.mapToEntity(recipeDto)));
    }

    public RecipeDto addFoodToRecipe(Long recipeId, Long foodId, Double weight) {
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
        return recipeMapper.mapToDto(recipe, weight);
    }
}
