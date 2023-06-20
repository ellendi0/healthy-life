package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.FoodToMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodToMealRepository extends JpaRepository<FoodToMeal, Long>{
    void deleteByMealIdAndFoodId(long mealId, long foodId);
}
