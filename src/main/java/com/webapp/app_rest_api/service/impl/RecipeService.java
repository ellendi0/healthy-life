package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.FoodRecipe;
import com.webapp.app_rest_api.model.Recipe;
import com.webapp.app_rest_api.repository.FoodRecipeRepository;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.repository.RecipeRepository;
import com.webapp.app_rest_api.service.IFoodService;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService implements IRecipeService {

    private RecipeRepository recipeRepository;
    private FoodRepository foodRepository;
    private FoodRecipeRepository foodRecipeRepository;
    private IFoodService foodService;

    public RecipeService(RecipeRepository recipeRepository, FoodRepository foodRepository, FoodRecipeRepository foodRecipeRepository, IFoodService foodService) {
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;
        this.foodRecipeRepository = foodRecipeRepository;
        this.foodService = foodService;
    }


    public void countNutritiousFromFoodList(Recipe recipe, List<FoodRecipe> foodRecipes){

        double calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        double weight = 0;

        for (FoodRecipe foodRecipe : foodRecipes) {
            Food food = foodService.getFoodWithGivenWeight(foodRecipe.getId(), foodRecipe.getWeight());
            calories += food.getNumberOfCalories();
            proteins += food.getNumberOfProtein();
            fats += food.getNumberOfFat();
            carbohydrates += food.getNumberOfCarbohydrate();
            weight += food.getWeight();
        }

        recipe.setNumberOfCalories(calories);
        recipe.setNumberOfProtein(proteins);
        recipe.setNumberOfFat(fats);
        recipe.setNumberOfCarbohydrate(carbohydrates);
        recipe.setWeight(weight);
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe countNutritiousFromRecipeForWeight(Recipe recipe, double weight) {
        Recipe recipeWithGivenWeight = new Recipe();

        recipeWithGivenWeight.setNumberOfCalories(recipe.getNumberOfCalories() * weight / recipe.getWeight());
        recipeWithGivenWeight.setNumberOfProtein(recipe.getNumberOfProtein() * weight / recipe.getWeight());
        recipeWithGivenWeight.setNumberOfFat(recipe.getNumberOfFat() * weight / recipe.getWeight());
        recipeWithGivenWeight.setNumberOfCarbohydrate(recipe.getNumberOfCarbohydrate() * weight / recipe.getWeight());
        recipeWithGivenWeight.setWeight(weight);

        return recipeWithGivenWeight;
    }

    @Override
    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
    }

    @Override
    public List<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(long id, Recipe recipe) {
        Recipe updateRecipe = recipeRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(id)));
        updateRecipe.setName(recipe.getName());

        return recipeRepository.save(updateRecipe);
    }

    @Override
    public String deleteRecipe(long id) {
        recipeRepository.deleteById(id);
        return "Recipe deleted successfully";
    }

}
