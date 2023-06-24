package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.FoodToRecipe;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.mapper.RecipeMapper;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.repository.FoodToRecipeRepository;
import com.webapp.app_rest_api.repository.RecipeRepository;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeService{

    private RecipeRepository recipeRepository;
    private FoodService foodService;
    private RecipeMapper recipeMapper;
    private FoodToRecipeService foodToRecipeService;

    public RecipeService(RecipeRepository recipeRepository, FoodService foodService, RecipeMapper recipeMapper, FoodToRecipeService foodToRecipeService) {
        this.recipeRepository = recipeRepository;
        this.foodService = foodService;
        this.recipeMapper = recipeMapper;
        this.foodToRecipeService = foodToRecipeService;
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
    }

    public List<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    public Recipe createUpdateRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe addFoodToRecipe(Long recipeId, Long foodId, double weight) {
        Recipe recipe = getRecipeById(recipeId);
        FoodToRecipe foodToRecipe = foodToRecipeService.getFoodByRecipeIdAndFoodId(recipeId, foodId);

        if(Objects.isNull(foodToRecipe)){
            Food food = foodService.getFoodById(foodId);
            foodToRecipe = new FoodToRecipe(food, recipe, weight);
            recipe.getFood().add(foodToRecipe);
            food.getRecipe().add(foodToRecipe);
        }else{
            foodToRecipe.setWeight(foodToRecipe.getWeight() + weight);
        }
        foodToRecipeService.createUpdateFoodToRecipe(foodToRecipe);
        recipeRepository.save(recipe);
//        countNutritiousFromFoodList(recipeDto);
        return recipe;
    }

    public Recipe updateRecipe(Long recipeId, Recipe recipe) {
        Recipe recipeNew = getRecipeById(recipeId);
        recipeNew.setName(recipeNew.getName());
        return recipeRepository.save(recipeNew);
    }

    public Recipe updateFoodInRecipe(Long recipeId, Long foodId, Double weight) {
        foodToRecipeService.updateFoodToRecipe(recipeId, foodId, weight);
        return getRecipeById(recipeId);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    @Transactional
    public void deleteFoodFromRecipe(Long recipeId, Long foodId) {
        foodToRecipeService.deleteByRecipeIdAndFoodId(recipeId, foodId);
    }

    public void countNutritiousFromFoodList(RecipeDto recipeDto) {
        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;

        if(recipeDto.getFood() != null) {
            for (FoodDto foodDto : recipeDto.getFood()) {
                calories += foodDto.getNumberOfCalories();
                proteins += foodDto.getNumberOfProtein();
                fats += foodDto.getNumberOfFat();
                carbohydrates += foodDto.getNumberOfCarbohydrate();
                weight += foodDto.getWeight();
            }
        }

        recipeDto.setNumberOfCalories(calories);
        recipeDto.setNumberOfProtein(proteins);
        recipeDto.setNumberOfFat(fats);
        recipeDto.setNumberOfCarbohydrate(carbohydrates);
        recipeDto.setWeight(weight);
        recipeRepository.save(recipeMapper.mapToEntity(recipeDto));
    }

    public RecipeDto getRecipeWithGivenWeight(long recipeId, double weight) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(recipeId)));
        RecipeDto recipeDto = recipeMapper.mapToDto(recipe);
        recipeDto.setId(recipe.getId());
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
