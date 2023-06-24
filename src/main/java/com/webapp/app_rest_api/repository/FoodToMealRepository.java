package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.FoodToMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodToMealRepository extends JpaRepository<FoodToMeal, Long>{
    Optional<FoodToMeal> getFoodToMealByMealIdAndFoodId(long mealId, long foodId);
    void deleteFoodToMealByMeal_IdAndFood_Id(long mealId, long foodId);
    Boolean existsByMealIdAndFoodId(long mealId, long foodId);
}
