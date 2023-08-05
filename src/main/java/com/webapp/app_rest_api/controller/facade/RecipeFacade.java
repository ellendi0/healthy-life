package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
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

    public RecipeDto createRecipe(PersonalInfo personalInfo, RecipeDto recipeDto) {
        return recipeMapper.mapToDto(recipeService.createRecipe(personalInfo, recipeMapper.mapToEntity(recipeDto)));
    }

    public RecipeDto getPublicRecipe(Long id) {
        return recipeMapper.mapToDto(recipeService.getPublicRecipe(id));
    }

    public RecipeDto getRecipeForUser(PersonalInfo personalInfo, Long userId) {
        return recipeMapper.mapToDto(recipeService.getRecipeForUser(personalInfo, userId));
    }

    public List<RecipeDto> getAllPublicRecipe() {
        return recipeService.getAllPublicRecipe().stream()
                .map(recipeMapper::mapToDto)
                .toList();
    }

    public List<RecipeDto> getAllRecipe(PersonalInfo personalInfo) {
        return recipeService.getAllRecipe(personalInfo).stream()
                .map(recipeMapper::mapToDto)
                .toList();
    }

    public RecipeDto updateRecipe(PersonalInfo personalInfo, Long id, RecipeDto recipeDto) {
        return recipeMapper.mapToDto(recipeService.updateRecipe(personalInfo, id, recipeMapper.mapToEntity(recipeDto)));
    }

    public RecipeDto addFoodToRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId, Double weight) {
        recipeService.addFoodToRecipe(personalInfo, recipeId, foodId, weight);
        countNutritiousFromFoodList(recipeId);
        return recipeMapper.mapToDto(recipeService.getRecipeForUser(personalInfo, recipeId));
    }

    public RecipeDto updateFoodInRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId, Double weight) {
        recipeService.updateFoodInRecipe(personalInfo, recipeId, foodId, weight);
        countNutritiousFromFoodList(recipeId);
        return recipeMapper.mapToDto(recipeService.getRecipeForUser(personalInfo, recipeId));
    }

    public RecipeDto deleteRecipe(PersonalInfo personalInfo, Long id) {
        RecipeDto recipeDto = recipeMapper.mapToDto(recipeService.getRecipeForUser(personalInfo, id));
        recipeService.deleteRecipe(personalInfo, id);
        return recipeDto;
    }

    public String deleteAllRecipe(PersonalInfo personalInfo) {
        recipeService.deleteAllRecipe(personalInfo);
        return "All recipes have been deleted";
    }

    public RecipeDto deleteFoodFromRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId) {
        recipeService.deleteFoodFromRecipe(personalInfo, recipeId, foodId);
        return countNutritiousFromFoodList(recipeId);
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

        if (recipeDto.getFood() != null) {
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

        return recipeMapper.mapToDto(recipeService.createSaveRecipe(recipe));
    }

    public RecipeDto getRecipeWithGivenWeight(Long recipeId, Double weight) {
        Recipe recipe = recipeService.getPublicRecipe(recipeId);
        return recipeMapper.mapToDto(recipe, weight);
    }
}
