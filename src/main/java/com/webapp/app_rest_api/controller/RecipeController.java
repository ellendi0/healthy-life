package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.RecipeFacade;
import com.webapp.app_rest_api.dto.RecipeDto;
import com.webapp.app_rest_api.model.entities.User;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeFacade recipeFacade;

    public RecipeController(RecipeFacade recipeFacade) {
        this.recipeFacade = recipeFacade;
    }

    @GetMapping({"{id}"})
    public RecipeDto getRecipeByIdForUser(@AuthenticationPrincipal User user, @PathVariable Long id) {
        return recipeFacade.getRecipeForUser(user.getPersonalInfo(), id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getRecipe/{id}")
    public RecipeDto getRecipeById(@PathVariable Long id) {
        return recipeFacade.getPublicRecipe(id);
    }

    @GetMapping
    public List<RecipeDto> getAllUserRecipe(@AuthenticationPrincipal User user) {
        return recipeFacade.getAllRecipe(user.getPersonalInfo());
    }

    @GetMapping("/allPublicRecipe")
    public List<RecipeDto> getAllPublicRecipe() {
        return recipeFacade.getAllPublicRecipe();
    }

    @PostMapping
    public RecipeDto createRecipe(@AuthenticationPrincipal User user,
                                  @Valid @RequestBody RecipeDto recipeDto) {
        return recipeFacade.createRecipe(user.getPersonalInfo(), recipeDto);
    }

    @PutMapping({"{id}"})
    public RecipeDto updateRecipe(@AuthenticationPrincipal User user,
                                  @Valid @PathVariable Long id,
                                  @RequestBody RecipeDto recipeDto) {
        return recipeFacade.updateRecipe(user.getPersonalInfo(), id, recipeDto);
    }

    @GetMapping({"{id}/"})
    public RecipeDto getRecipeWithGivenWeight(@PathVariable Long id,
                                              @RequestParam(value = "weight") Double weight) {
        return recipeFacade.getRecipeWithGivenWeight(id, weight);
    }

    @DeleteMapping({"{id}"})
    public RecipeDto deleteRecipe(@AuthenticationPrincipal User user,
                                  @PathVariable Long id) {
        System.out.println("The recipe with id " + id + " was deleted.");
        return recipeFacade.deleteRecipe(user.getPersonalInfo(), id);
    }

    @PostMapping({"{id}/addFood/{idFood}"})
    public RecipeDto addFoodToRecipe(@AuthenticationPrincipal User user,
                                     @PathVariable Long id,
                                     @PathVariable Long idFood,
                                     @RequestParam(value = "weight") Double weight) {
        return recipeFacade.addFoodToRecipe(user.getPersonalInfo(), id, idFood, weight);
    }

    @PutMapping({"{id}/updateFoodInRecipe/{idFood}"})
    public RecipeDto updateFoodInRecipe(@AuthenticationPrincipal User user,
                                        @PathVariable Long id,
                                        @PathVariable Long idFood,
                                        @RequestParam(value = "weight") Double weight) {
        return recipeFacade.updateFoodInRecipe(user.getPersonalInfo(), id, idFood, weight);
    }

    @DeleteMapping({"{id}/deleteFood/{idFood}"})
    public RecipeDto deleteFoodFromRecipe(@AuthenticationPrincipal User user,
                                          @PathVariable Long id,
                                          @PathVariable Long idFood) {
        return recipeFacade.deleteFoodFromRecipe(user.getPersonalInfo(), id, idFood);
    }

    @DeleteMapping({"/deleteAllRecipe"})
    public String deleteAllFoodFromRecipe(@AuthenticationPrincipal User user) {
        return recipeFacade.deleteAllRecipe(user.getPersonalInfo());
    }
}