package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.FoodRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRecipeRepository extends JpaRepository<FoodRecipe, Long> {
    List<FoodRecipe> findAllFoodByRecipe_id(long id);
    FoodRecipe findFoodByRecipe_idAndFood_Id(long id, long foodId);
}
