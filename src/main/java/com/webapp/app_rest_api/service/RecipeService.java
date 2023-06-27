package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.mapper.RecipeMapper;
import com.webapp.app_rest_api.repository.RecipeRepository;
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

    public Recipe getRecipe(Long id) {
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
        Recipe recipe = getRecipe(recipeId);
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
        Recipe recipeNew = getRecipe(recipeId);
        recipeNew.setName(recipe.getName());
        return recipeRepository.save(recipeNew);
    }

    public Recipe updateFoodInRecipe(Long recipeId, Long foodId, Double weight) {
        foodToRecipeService.updateFoodToRecipe(recipeId, foodId, weight);
        return getRecipe(recipeId);
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public void deleteAllRecipe() {
        recipeRepository.deleteAll();
    }

    @Transactional
    public void deleteFoodFromRecipe(Long recipeId, Long foodId) {
        foodToRecipeService.deleteByRecipeIdAndFoodId(recipeId, foodId);
    }
}
