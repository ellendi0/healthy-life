package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.FoodRecipe;
import com.webapp.app_rest_api.model.Recipe;
import com.webapp.app_rest_api.repository.FoodRecipeRepository;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.repository.RecipeRepository;
import com.webapp.app_rest_api.service.IFoodRecipeService;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodRecipeService implements IFoodRecipeService {

    private FoodRecipeRepository foodRecipeRepository;
    private FoodRepository foodRepository;
    private RecipeRepository recipeRepository;
    private IRecipeService recipeService;

    public FoodRecipeService(FoodRecipeRepository foodRecipeRepository, FoodRepository foodRepository, RecipeRepository recipeRepository, IRecipeService recipeService) {
        this.foodRecipeRepository = foodRecipeRepository;
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    @Override
    public FoodRecipe addFoodToRecipe(long idRecipe, long idFood, double weight) {
        Food food = foodRepository.findById(idFood).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(idFood)));
        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(idRecipe)));
        FoodRecipe foodRecipe;

        if(foodRecipeRepository.findFoodByRecipe_idAndFood_Id(idRecipe, idFood) != null) {
            foodRecipe = foodRecipeRepository.findFoodByRecipe_idAndFood_Id(idRecipe, idFood);
            foodRecipe.setWeight(foodRecipe.getWeight() + weight);
        }else {
            foodRecipe = new FoodRecipe();
            foodRecipe.setFood(food);
            foodRecipe.setRecipe(recipe);
            foodRecipe.setWeight(weight);
        }
        foodRecipeRepository.save(foodRecipe);
        recipeService.countNutritiousFromFoodList(recipe, foodRecipeRepository.findAllFoodByRecipe_id(idRecipe));
        return foodRecipe;
    }

    @Override
    public List<Food> getAllFoodFromRecipe(long id) {
        List<FoodRecipe> foodRecipes = foodRecipeRepository.findAllFoodByRecipe_id(id);
        List<Food> foods = foodRecipes.stream().map(FoodRecipe::getFood).toList();
        return foods;
    }

    @Override
    public void deleteFoodFromRecipe(long id, long foodId) {
        foodRecipeRepository.delete(foodRecipeRepository.findFoodByRecipe_idAndFood_Id(id, foodId));
    }

    @Override
    public FoodRecipe changeFoodWeight(long idRecipe, long idFood, double weight) {
        Food food = foodRepository.findById(idFood).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(idFood)));

        Recipe recipe = recipeRepository.findById(idRecipe).orElseThrow(()
                -> new ResourceNotFoundException("Recipe", "id", String.valueOf(idRecipe)));

        FoodRecipe foodRecipe = foodRecipeRepository.findFoodByRecipe_idAndFood_Id(idRecipe, idFood);
        if (foodRecipe == null) {
            throw new NullPointerException(String.format("The food with id %s not found in recipe with id %s", idFood, idRecipe));
        }
        foodRecipe.setWeight(weight);
        foodRecipeRepository.save(foodRecipe);
        recipeService.countNutritiousFromFoodList(recipe, foodRecipeRepository.findAllFoodByRecipe_id(idRecipe));
        return foodRecipe;
    }
}
