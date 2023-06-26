package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.MealFacade;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.service.impl.MealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {
    private final MealFacade mealFacade;

    public MealController(MealFacade mealFacade) {
        this.mealFacade = mealFacade;
    }

    @GetMapping("/{id}")
    public MealDto getMeal(@PathVariable long id){
        return mealFacade.getMeal(id);
    }

    @GetMapping
    public List<MealDto> getAllMeal(){
        return mealFacade.getAllMeals();
    }

    @PostMapping
    public MealDto createMeal(@RequestBody Meal meal){
        return mealFacade.createUpdateMeal(meal);
    }

    @PostMapping({"/{mealId}/addfood/{foodId}/{weight}"})
    public MealDto addFoodToMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return mealFacade.addFoodToMeal(mealId, foodId, weight);
    }

    @PostMapping({"/{mealId}/addrecipe/{recipeId}/{weight}"})
    public MealDto addRecipeToMeal(@PathVariable long mealId, @PathVariable long recipeId, @PathVariable double weight){
        return mealFacade.addRecipeToMeal(mealId, recipeId, weight);
    }

    @PutMapping("/{mealId}/changerecipe/{recipeId}/{weight}")
    public MealDto changeRecipeInMeal(@PathVariable long mealId, @PathVariable long recipeId, @PathVariable double weight){
        return mealFacade.updateRecipeInMeal(mealId, recipeId, weight);
    }

    @PutMapping("/{mealId}/changefood/{foodId}/{weight}")
    public MealDto changeFoodInMeal(@PathVariable long mealId, @PathVariable long foodId, @PathVariable double weight){
        return mealFacade.updateFoodInMeal(mealId, foodId, weight);
    }

    @DeleteMapping("/{mealId}/deleterecipe/{recipeId}")
    public MealDto deleteRecipeFromMeal(@PathVariable long mealId, @PathVariable long recipeId){
        System.out.println("The recipe with id " + recipeId + " was deleted from meal");
        return mealFacade.deleteRecipeFromMeal(mealId, recipeId);
    }

    @DeleteMapping({"/{mealId}/deletefood/{foodId}"})
    public MealDto deleteFoodFromMeal(@PathVariable long mealId, @PathVariable long foodId){
        System.out.println("The food with id " + foodId + " was deleted from meal");
        return mealFacade.deleteFoodFromMeal(mealId, foodId);
    }

    @DeleteMapping("/{id}")
    public MealDto deleteMeal(@PathVariable long id){
        System.out.println("The meal with id " + id + " was deleted");
        return mealFacade.deleteMeal(id);
    }

    @DeleteMapping("/deleteall")
    public String deleteAllMeals(){
        mealFacade.deleteAllMeals();
        return ("All meals are successfully deleted");
    }
}
