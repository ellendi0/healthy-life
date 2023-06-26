package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.entities.FoodToMeal;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.repository.FoodToMealRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoodToMealService {
    private FoodToMealRepository foodToMealRepository;

    public FoodToMealService(FoodToMealRepository foodToMealRepository) {
        this.foodToMealRepository = foodToMealRepository;
    }

    public FoodToMeal getFoodToMealById(Long mealId, Long foodId){
        return foodToMealRepository.getFoodToMealByMealIdAndFoodId(mealId, foodId).orElse(null);
    }

    public FoodToMeal createUpdateFoodToMeal(FoodToMeal foodToMeal){
        return foodToMealRepository.save(foodToMeal);
    }
    public FoodToMeal updateFoodToMeal(Long mealId, Long foodId, Double weight){
        if(!foodToMealRepository.existsByMealIdAndFoodId(mealId, foodId)){
            throw new ResourceNotFoundException(
                    "Food", "id", String.valueOf(foodId), "Meal", "id", String.valueOf(mealId));
        }

        FoodToMeal foodToMeal = getFoodToMealById(mealId, foodId);
        foodToMeal.setWeight(weight);

        return foodToMealRepository.save(foodToMeal);
    }

    public void deleteFoodToMeal(Long mealId, Long foodId){
        if(!foodToMealRepository.existsByMealIdAndFoodId(mealId, foodId)){
            throw new ResourceNotFoundException(
                    "Food", "id", String.valueOf(foodId), "Meal", "id", String.valueOf(mealId));
        }

        foodToMealRepository.deleteFoodToMealByMeal_IdAndFood_Id(mealId, foodId);
    }
}
