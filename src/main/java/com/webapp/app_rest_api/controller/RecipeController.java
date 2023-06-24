package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.service.impl.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// response<entity> delete
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/{id}"})
    public Recipe getRecipeById(@PathVariable long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.createUpdateRecipe(recipe);
    }

    @PutMapping({"/{id}"})
    public Recipe updateRecipe(@PathVariable long id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping({"/{id}"})
    public String deleteRecipe(@PathVariable long id) {
        recipeService.deleteRecipe(id);
        return "The recipe is successfully deleted";
    }

    @PostMapping({"/{id}/addfood/{idFood}/{weight}"})
    public Recipe addFoodToRecipe(@PathVariable long id, @PathVariable long idFood, @PathVariable double weight) {
        return recipeService.addFoodToRecipe(id, idFood, weight);
    }

    @PutMapping({"/{id}/updaterecipe/{idFood}/{weight}"})
    public Recipe updateFoodInRecipe(@PathVariable long id, @PathVariable long idFood, @PathVariable double weight) {
        return recipeService.updateFoodInRecipe(id, idFood, weight);
    }

    @DeleteMapping({"/{id}/deletefood/{idFood}"})
    public String deleteFoodFromRecipe(@PathVariable long id, @PathVariable long idFood) {
        recipeService.deleteFoodFromRecipe(id, idFood);
        return "The food is successfully deleted";
    }
}