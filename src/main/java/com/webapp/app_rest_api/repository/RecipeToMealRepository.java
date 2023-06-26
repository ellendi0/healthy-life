package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.RecipeToMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeToMealRepository extends JpaRepository<RecipeToMeal, Long> {
    Optional<RecipeToMeal> getRecipeToMealByMealIdAndRecipeId(Long mealId, Long recipeId);
    void deleteRecipeToMealByMealIdAndRecipeId(Long mealId, Long recipeId);
    Boolean existsByMealIdAndRecipeId(Long mealId, Long recipeId);
}
