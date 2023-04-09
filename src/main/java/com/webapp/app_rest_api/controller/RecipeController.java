package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.FoodRecipe;
import com.webapp.app_rest_api.model.Recipe;
import com.webapp.app_rest_api.service.IFoodRecipeService;
import com.webapp.app_rest_api.service.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private IRecipeService iRecipeService;
    private IFoodRecipeService iFoodRecipeService;

    public RecipeController(IRecipeService iRecipeService, IFoodRecipeService iFoodRecipeService) {
        this.iRecipeService = iRecipeService;
        this.iFoodRecipeService = iFoodRecipeService;
    }

    @GetMapping({"/{idRecipe}"})
    public ResponseEntity<Recipe> getRecipeById(@PathVariable long idRecipe) {
        return new ResponseEntity<>(iRecipeService.getRecipeById(idRecipe), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipe() {
        return new ResponseEntity<>(iRecipeService.getAllRecipe(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(iRecipeService.createRecipe(recipe), HttpStatus.CREATED);
    }

    @PutMapping({"/{idRecipe}"})
    public ResponseEntity<Recipe> updateRecipe(@PathVariable long idRecipe, @RequestBody Recipe recipe) {
        return new ResponseEntity<>(iRecipeService.updateRecipe(idRecipe, recipe), HttpStatus.OK);
    }

    @DeleteMapping({"/{idRecipe}"})
    public ResponseEntity<String> deleteRecipe(@PathVariable long idRecipe) {
        iRecipeService.deleteRecipe(idRecipe);
        return new ResponseEntity<>("The recipe is successfully deleted", HttpStatus.OK);
    }

    @PostMapping({"/{idRecipe}/food/{idFood}"})
    public ResponseEntity<FoodRecipe> addFoodToRecipe(@PathVariable long idRecipe, @PathVariable long idFood, @RequestBody FoodRecipe foodRecipe) {
        return new ResponseEntity<>(iFoodRecipeService.addFoodToRecipe(idRecipe, idFood, foodRecipe.getWeight()), HttpStatus.OK);
    }

    @GetMapping({"/{idRecipe}/food"})
    public ResponseEntity<List<Food>> getAllFoodFromRecipe(@PathVariable long idRecipe) {
        return new ResponseEntity<>(iFoodRecipeService.getAllFoodFromRecipe(idRecipe), HttpStatus.OK);
    }

    @DeleteMapping({"/{idRecipe}/food/{idFood}"})
    public ResponseEntity<String> deleteFoodFromRecipe(@PathVariable long idRecipe, @PathVariable long idFood) {
        iFoodRecipeService.deleteFoodFromRecipe(idRecipe, idFood);
        return new ResponseEntity<>("The food is successfully deleted", HttpStatus.OK);
    }

    @PutMapping({"/{idRecipe}/food/{idFood}"})
    public ResponseEntity<FoodRecipe> changeFoodWeight(@PathVariable long idRecipe, @PathVariable long idFood, @RequestBody FoodRecipe foodRecipe){
        return new ResponseEntity<>(iFoodRecipeService.changeFoodWeight(idRecipe, idFood, foodRecipe.getWeight()), HttpStatus.OK);
    }
}
