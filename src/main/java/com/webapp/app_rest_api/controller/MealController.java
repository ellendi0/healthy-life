package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.service.impl.MealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {
    private MealService mealService;

    public MealController(MealService MealService) {
        this.mealService = MealService;
    }

    @GetMapping("/{id}")
    public Meal getMeal(@PathVariable long id){
        return mealService.getMeal(id);
    }

    @GetMapping
    public List<Meal> getAllMeal(){
        return mealService.getAllMeals();
    }

    @PostMapping
    public Meal createMeal(@RequestBody Meal meal){
        return mealService.createUpdateMeal(meal);
    }

    @PostMapping({"/{mealId}/addfood/{foodId}/{weight}"})
    public Meal addFoodToMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return mealService.addFoodToMeal(mealId, foodId, weight);
    }

    @PostMapping({"/{mealId}/addrecipe/{recipeId}/{weight}"})
    public Meal addRecipeToMeal(@PathVariable long mealId, @PathVariable long recipeId, @PathVariable double weight){
        return mealService.addRecipeToMeal(mealId, recipeId, weight);
    }

    @PutMapping("/{mealId}/changerecipe/{recipeId}/{weight}")
    public Meal changeRecipeInMeal(@PathVariable long mealId, @PathVariable long recipeId, @PathVariable double weight){
        return mealService.updateRecipeInMeal(mealId, recipeId, weight);
    }

    @PutMapping("/{mealId}/changefood/{foodId}/{weight}")
    public Meal changeFoodInMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return mealService.updateFoodInMeal(mealId, foodId, weight);
    }

    @DeleteMapping("/{mealId}/deleterecipe/{recipeId}")
    public String deleteRecipeFromMeal(@PathVariable long mealId, @PathVariable long recipeId){
        mealService.deleteRecipeFromMeal(mealId, recipeId);
        return "The recipe is successfully deleted";
    }

    @DeleteMapping({"/{mealId}/deletefood/{foodId}"})
    public String deleteFoodFromMeal(@PathVariable long mealId, @PathVariable long foodId){
        mealService.deleteFoodFromMeal(mealId, foodId);
        return ("The food is successfully deleted");
    }

    @DeleteMapping("/{id}")
    public String deleteMeal(@PathVariable long id){
        mealService.deleteMeal(id);
        return ("The meal is successfully deleted");
    }

    @DeleteMapping("/deleteall")
    public String deleteAllMeals(){
        mealService.deleteAllMeals();
        return ("All meals are successfully deleted");
    }
}
