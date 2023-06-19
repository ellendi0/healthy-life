package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.Meal;
import com.webapp.app_rest_api.service.IMealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {
    private IMealService iMealService;

    public MealController(IMealService iMealService) {
        this.iMealService = iMealService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDto> getMeal(@PathVariable long id){
        return new ResponseEntity<>(iMealService.getMeal(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MealDto>> getAllMeal(){
        return new ResponseEntity<>(iMealService.getAllMeals(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal){
        return new ResponseEntity<>(iMealService.createMeal(meal), HttpStatus.CREATED);
    }

    @PostMapping({"/{mealId}/addfood/{foodId}/{weight}"})
    public ResponseEntity<MealDto> addFoodToMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return new ResponseEntity<>(iMealService.addFoodToMeal(mealId, foodId, weight), HttpStatus.OK);
    }

    @PostMapping({"/{mealId}/addrecipe/{recipeId}/{weight}"})
    public ResponseEntity<MealDto> addRecipeToMeal(@PathVariable long mealId, @PathVariable long recipeId, @PathVariable double weight){
        return new ResponseEntity<>(iMealService.addRecipeToMeal(mealId, recipeId, weight), HttpStatus.OK);
    }

    @PutMapping("/{mealId}/change/{recipeId}/{weight}")
    public ResponseEntity<MealDto> changeRecipeInMeal(@PathVariable long mealId, @PathVariable long recipeId, @PathVariable double weight){
        return new ResponseEntity<>(iMealService.updateRecipeInMeal(mealId, recipeId, weight), HttpStatus.OK);
    }

    @PutMapping("/{mealId}/change/{foodId}/{weight}")
    public ResponseEntity<MealDto> changeFoodInMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return new ResponseEntity<>(iMealService.updateFoodInMeal(mealId, foodId, weight), HttpStatus.OK);
    }

    @DeleteMapping("/{mealId}/delete/{recipeId}")
    public ResponseEntity<String> deleteRecipeFromMeal(@PathVariable long mealId, @PathVariable long recipeId){
        iMealService.deleteRecipeFromMeal(mealId, recipeId);
        return new ResponseEntity<>("The recipe is successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping({"/{mealId}/deletefood/{foodId}"})
    public ResponseEntity<String> deleteFoodFromMeal(@PathVariable long mealId, @PathVariable long foodId){
        iMealService.deleteFoodFromMeal(mealId, foodId);
        return new ResponseEntity<>("The food is successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable long id){
        iMealService.deleteMeal(id);
        return new ResponseEntity<>("The meal is successfully deleted", HttpStatus.OK);
    }
}
