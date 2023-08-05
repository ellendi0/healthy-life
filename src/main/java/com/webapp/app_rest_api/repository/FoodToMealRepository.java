package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodToMealRepository extends JpaRepository<FoodToMeal, Long>{
    Optional<FoodToMeal> findFoodToMealByMealIdAndFoodId(Long mealId, Long foodId);
    void deleteFoodToMealByMeal_IdAndFood_Id(Long mealId, Long foodId);
    Boolean existsByMealIdAndFoodId(Long mealId, Long foodId);
}
