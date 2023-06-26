package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.RecipeFacade;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.Recipe;
import com.webapp.app_rest_api.service.impl.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private RecipeFacade recipeFacade;

    public RecipeController(RecipeFacade recipeFacade) {
        this.recipeFacade = recipeFacade;
    }

    @GetMapping({"/{id}"})
    public RecipeDto getRecipeById(@PathVariable Long id) {
        return recipeFacade.getRecipe(id);
    }

    @GetMapping
    public List<RecipeDto> getAllRecipe() {
        return recipeFacade.getAllRecipe();
    }

    @PostMapping
    public RecipeDto createRecipe(@RequestBody Recipe recipe) {
        return recipeFacade.createRecipe(recipe);
    }

    @PutMapping({"/{id}"})
    public RecipeDto updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        return recipeFacade.updateRecipe(id, recipe);
    }

    @DeleteMapping({"/{id}"})
    public RecipeDto deleteRecipe(@PathVariable Long id) {
        System.out.println("The recipe with id " + id + " was deleted.");
        return recipeFacade.deleteRecipe(id);
    }

    @PostMapping({"/{id}/addfood/{idFood}/{weight}"})
    public RecipeDto addFoodToRecipe(@PathVariable Long id, @PathVariable Long idFood, @PathVariable Double weight) {
        return recipeFacade.addFoodToRecipe(id, idFood, weight);
    }

    @PutMapping({"/{id}/updaterecipe/{idFood}/{weight}"})
    public RecipeDto updateFoodInRecipe(@PathVariable Long id, @PathVariable Long idFood, @PathVariable Double weight) {
        return recipeFacade.updateFoodInRecipe(id, idFood, weight);
    }

    @DeleteMapping({"/{id}/deletefood/{idFood}"})
    public RecipeDto deleteFoodFromRecipe(@PathVariable long id, @PathVariable long idFood) {
        System.out.println("The food with id " + idFood + " was deleted from recipe.");
        return recipeFacade.deleteFoodFromRecipe(id, idFood);
    }
}