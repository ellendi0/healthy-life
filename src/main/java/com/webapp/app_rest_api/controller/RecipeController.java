package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.Recipe;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private IRecipeService iRecipeService;

    public RecipeController(IRecipeService iRecipeService) {
        this.iRecipeService = iRecipeService;
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable long id) {
        return new ResponseEntity<>(iRecipeService.getRecipeById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipe() {
        return new ResponseEntity<>(iRecipeService.getAllRecipe(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(iRecipeService.createRecipe(recipe), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<RecipeDto> updateRecipe(@PathVariable long id, @RequestBody RecipeDto recipeDto) {
        return new ResponseEntity<>(iRecipeService.updateRecipe(id, recipeDto), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteRecipe(@PathVariable long id) {
        iRecipeService.deleteRecipe(id);
        return new ResponseEntity<>("The recipe is successfully deleted", HttpStatus.OK);
    }

    @PostMapping({"/{id}/addfood/{idFood}/{weight}"})
    public ResponseEntity<RecipeDto> addFoodToRecipe(@PathVariable long id, @PathVariable long idFood, @PathVariable double weight) {
        return new ResponseEntity<>(iRecipeService.addFoodToRecipe(id, idFood, weight), HttpStatus.OK);
    }

    @PutMapping({"/{id}/updaterecipe/{idFood}/{weight}"})
    public ResponseEntity<RecipeDto> updateFoodInRecipe(@PathVariable long id, @PathVariable long idFood, @PathVariable double weight) {
        return new ResponseEntity<>(iRecipeService.updateFoodInRecipe(id, idFood, weight), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}/deletefood/{idFood}"})
    public ResponseEntity<String> deleteFoodFromRecipe(@PathVariable long id, @PathVariable long idFood) {
        iRecipeService.deleteFoodFromRecipe(id, idFood);
        return new ResponseEntity<>("The food is successfully deleted", HttpStatus.OK);
    }
}