package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.connection.FoodToRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodToRecipeRepository extends JpaRepository<FoodToRecipe, Long> {
    Optional<FoodToRecipe> getFoodToRecipeByRecipeIdAndFoodId(Long recipeId, Long foodId);
    void deleteFoodToRecipeByRecipe_IdAndFood_Id(Long recipeId, Long foodId);
    Boolean existsByRecipe_IdAndFood_Id(Long recipeId, Long foodId);
}
