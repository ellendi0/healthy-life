package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.MealFacade;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.model.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/meal")
public class MealController {
    private final MealFacade mealFacade;

    public MealController(MealFacade mealFacade) {
        this.mealFacade = mealFacade;
    }

    @GetMapping("/dayDiet/{dayDietId}")
    public List<MealDto> getAllMeal(@AuthenticationPrincipal User user,
                                    @PathVariable Long dayDietId){
        return mealFacade.getAllMeals(user, dayDietId);
    }

    @GetMapping("{mealId}")
    public MealDto getMealById(@AuthenticationPrincipal User user,
                                     @PathVariable Long mealId){
        return mealFacade.getMealByUserAndMealId(user, mealId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public MealDto createMeal(@Valid @RequestBody MealDto mealDto){
        return mealFacade.createUpdateMeal(mealDto);
    }

    @PostMapping("{mealId}/addFood/{foodId}")
    public MealDto addFoodToMeal(@AuthenticationPrincipal User user,
                                 @PathVariable(value = "mealId") Long mealId,
                                 @PathVariable(value = "foodId") Long foodId,
                                 @RequestParam(value = "weight") Double weight){
        return mealFacade.addFoodToMeal(user, mealId, foodId, weight);
    }

    @PostMapping({"{mealId}/addRecipe/{recipeId}"})
    public MealDto addRecipeToMeal(@AuthenticationPrincipal User user,
                                   @PathVariable(value = "mealId") Long mealId,
                                   @PathVariable(value = "recipeId") Long recipeId,
                                   @RequestParam(value = "weight") Double weight){
        return mealFacade.addRecipeToMeal(user, mealId, recipeId, weight);
    }

    @PutMapping("{mealId}/changeRecipe/{recipeId}")
    public MealDto changeRecipeInMeal(@AuthenticationPrincipal User user,
                                      @PathVariable Long mealId,
                                      @PathVariable Long recipeId,
                                      @RequestParam(value = "weight") Double weight){
        return mealFacade.updateRecipeInMeal(user, mealId, recipeId, weight);
    }

    @PutMapping("{mealId}/changeFood/{foodId}")
    public MealDto changeFoodInMeal(@AuthenticationPrincipal User user,
                                    @PathVariable Long mealId,
                                    @PathVariable Long foodId,
                                    @RequestParam(value = "weight") Double weight){
        return mealFacade.updateFoodInMeal(user, mealId, foodId, weight);
    }

    @DeleteMapping("{mealId}/deleteRecipe{recipeId}")
    public MealDto deleteRecipeFromMeal(@AuthenticationPrincipal User user,
                                        @PathVariable Long mealId,
                                        @PathVariable Long recipeId){
        return mealFacade.deleteRecipeFromMeal(user, mealId, recipeId);
    }

    @DeleteMapping({"{mealId}/deleteFood/{foodId}"})
    public MealDto deleteFoodFromMeal(@AuthenticationPrincipal User user,
                                      @PathVariable Long mealId,
                                      @PathVariable Long foodId){
        return mealFacade.deleteFoodFromMeal(user, mealId, foodId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{mealId}")
    public MealDto deleteMeal(@AuthenticationPrincipal User user,
                              @PathVariable Long mealId){
        return mealFacade.deleteMeal(user, mealId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteAll")
    public String deleteAllMeals() {
        return mealFacade.deleteAllMeals();
    }
}
