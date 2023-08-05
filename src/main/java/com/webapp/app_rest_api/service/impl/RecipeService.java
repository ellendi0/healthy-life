package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.model.enums.RecipeAccess;
import com.webapp.app_rest_api.repository.RecipeRepository;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RecipeService implements IRecipeService {
    private final RecipeRepository recipeRepository;
    private final FoodService foodService;
    private final FoodToRecipeService foodToRecipeService;

    public RecipeService(RecipeRepository recipeRepository,
                         FoodService foodService,
                         FoodToRecipeService foodToRecipeService) {
        this.recipeRepository = recipeRepository;
        this.foodService = foodService;
        this.foodToRecipeService = foodToRecipeService;
    }

    @Override
    public Recipe getRecipeForUser(PersonalInfo personalInfo, Long id) {
        return recipeRepository.findByPersonalInfo_IdAndId(personalInfo.getId(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
    }

    @Override
    public Recipe createRecipe(PersonalInfo personalInfo, Recipe recipe) {
        recipe.setPersonalInfo(personalInfo);
        recipe.setName(recipe.getName());
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe createSaveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe getPublicRecipe(Long id) {
        return recipeRepository.findByIdAndRecipeAccess(id, RecipeAccess.PUBLIC)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
    }

    @Override
    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
    }

    public Recipe getRecipeById(PersonalInfo personalInfo, Long id){
        if(recipeRepository.existsByIdAndRecipeAccess(id, RecipeAccess.PUBLIC)){
            return getPublicRecipe(id);
        } else if(recipeRepository.existsByPersonalInfo_IdAndId(personalInfo.getId(), id)){
            return getRecipeForUser(personalInfo, id);
        }else{
            throw new ResourceNotFoundException("Recipe", "id", String.valueOf(id));
        }
    }

    @Override
    public Recipe getRecipeByIdForUser(PersonalInfo personalInfo, Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
    }

    @Override
    public List<Recipe> getAllRecipe(PersonalInfo personalInfo) {
        return recipeRepository.findAllByPersonalInfo_Id(personalInfo.getId());
    }

    @Override
    public List<Recipe> getAllPublicRecipe() {
        return recipeRepository.findAllByRecipeAccess(RecipeAccess.PUBLIC).stream().toList();
    }

    @Override
    @Transactional
    public Recipe addFoodToRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId, Double weight) {
        Recipe recipe = getRecipeForUser(personalInfo, recipeId);
        FoodToRecipe foodToRecipe = foodToRecipeService.getFoodByRecipeIdAndFoodId(recipeId, foodId);

        if (Objects.isNull(foodToRecipe)) {
            Food food = foodService.getFoodById(foodId);
            foodToRecipe = new FoodToRecipe(food, recipe, weight);
            recipe.getFood().add(foodToRecipe);
            food.getRecipe().add(foodToRecipe);
        } else {
            foodToRecipe.setWeight(foodToRecipe.getWeight() + weight);
        }
        foodToRecipeService.createUpdateFoodToRecipe(foodToRecipe);
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(PersonalInfo personalInfo, Long recipeId, Recipe recipe) {
        Recipe recipeNew = getRecipeForUser(personalInfo, recipeId);
        recipeNew.setName(recipe.getName());
        recipeNew.setRecipeAccess(recipe.getRecipeAccess());
        return recipeRepository.save(recipeNew);
    }

    @Override
    public Recipe updateFoodInRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId, Double weight) {
        return foodToRecipeService.updateFoodToRecipe(recipeId, foodId, weight);
    }

    @Override
    public void deleteRecipe(PersonalInfo personalInfo, Long id) {
        recipeRepository.deleteByPersonalInfo_IdAndId(personalInfo.getId(), id);
    }

    @Override
    public void deleteAllRecipe(PersonalInfo personalInfo) {
        recipeRepository.deleteAllByPersonalInfo_Id(personalInfo.getId());
    }

    @Override
    @Transactional
    public void deleteFoodFromRecipe(PersonalInfo personalInfo, Long recipeId, Long foodId) {
        foodToRecipeService.deleteByRecipeIdAndFoodId(
                getRecipeForUser(personalInfo, recipeId).getId(), foodId);
    }
}
