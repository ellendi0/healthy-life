package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.Meal;
import com.webapp.app_rest_api.service.IMealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal")
public class MealController {
    private IMealService iMealService;

    public MealController(IMealService iMealService) {
        this.iMealService = iMealService;
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal){
        return new ResponseEntity<>(iMealService.createMeal(meal), HttpStatus.CREATED);
    }

    @PostMapping({"/{mealId}/addfood/{foodId}/{weight}"})
    public ResponseEntity<Meal> addFoodToMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return new ResponseEntity<>(iMealService.addFoodToMeal(mealId, foodId, weight), HttpStatus.OK);
    }
}
