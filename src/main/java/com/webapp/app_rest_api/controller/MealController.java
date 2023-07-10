package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.MealFacade;
import com.webapp.app_rest_api.dto.MealDto;
import jakarta.validation.Valid;
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
    public MealDto getMeal(@PathVariable Long id){
        return mealFacade.getMeal(id);
    }

    @GetMapping
    public List<MealDto> getAllMeal(){
        return mealFacade.getAllMeals();
    }

    @PostMapping
    public MealDto createMeal(@Valid @RequestBody MealDto mealDto){
        return mealFacade.createUpdateMeal(mealDto);
    }

    @PostMapping({"/{mealId}/addfood/{foodId}/{weight}"})
    public MealDto addFoodToMeal(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Double weight){
        return mealFacade.addFoodToMeal(mealId, foodId, weight);
    }

    @PostMapping({"/{mealId}/addrecipe/{recipeId}/{weight}"})
    public MealDto addRecipeToMeal(@PathVariable Long mealId, @PathVariable Long recipeId, @PathVariable Double weight){
        return mealFacade.addRecipeToMeal(mealId, recipeId, weight);
    }

    @PutMapping("/{mealId}/changerecipe/{recipeId}/{weight}")
    public MealDto changeRecipeInMeal(@PathVariable Long mealId, @PathVariable Long recipeId, @PathVariable Double weight){
        return mealFacade.updateRecipeInMeal(mealId, recipeId, weight);
    }

    @PutMapping("/{mealId}/changefood/{foodId}/{weight}")
    public MealDto changeFoodInMeal(@PathVariable Long mealId, @PathVariable Long foodId, @PathVariable Double weight){
        return mealFacade.updateFoodInMeal(mealId, foodId, weight);
    }

    @DeleteMapping("/{mealId}/deleterecipe/{recipeId}")
    public MealDto deleteRecipeFromMeal(@PathVariable Long mealId, @PathVariable Long recipeId){
        System.out.println("The recipe with id " + recipeId + " was deleted from meal");
        return mealFacade.deleteRecipeFromMeal(mealId, recipeId);
    }

    @DeleteMapping({"/{mealId}/deletefood/{foodId}"})
    public MealDto deleteFoodFromMeal(@PathVariable Long mealId, @PathVariable Long foodId){
        System.out.println("The food with id " + foodId + " was deleted from meal");
        return mealFacade.deleteFoodFromMeal(mealId, foodId);
    }

    @DeleteMapping("/{id}")
    public MealDto deleteMeal(@PathVariable Long id){
        System.out.println("The meal with id " + id + " was deleted");
        return mealFacade.deleteMeal(id);
    }

    @DeleteMapping("/deleteall")
    public String deleteAllMeals(){
        mealFacade.deleteAllMeals();
        return ("All meals are successfully deleted");
    }
}
