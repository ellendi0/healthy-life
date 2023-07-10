package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.connection.FoodToMeal;
import com.webapp.app_rest_api.repository.FoodToMealRepository;
import com.webapp.app_rest_api.service.IFoodToMealService;
import org.springframework.stereotype.Service;

@Service
public class FoodToMealService implements IFoodToMealService {
    private final FoodToMealRepository foodToMealRepository;

    public FoodToMealService(FoodToMealRepository foodToMealRepository) {
        this.foodToMealRepository = foodToMealRepository;
    }

    @Override
    public FoodToMeal getFoodToMealById(Long mealId, Long foodId) {
        return foodToMealRepository.getFoodToMealByMealIdAndFoodId(mealId, foodId).orElseThrow(()
                -> new ResourceNotFoundException(
                "Food", "id", String.valueOf(foodId), "Meal", "id", String.valueOf(mealId)));
    }

    @Override
    public FoodToMeal createUpdateFoodToMeal(FoodToMeal foodToMeal) {
        return foodToMealRepository.save(foodToMeal);
    }

    @Override
    public FoodToMeal updateFoodToMeal(Long mealId, Long foodId, Double weight) {
        FoodToMeal foodToMeal = getFoodToMealById(mealId, foodId);
        foodToMeal.setWeight(weight);
        return foodToMealRepository.save(foodToMeal);
    }

    @Override
    public void deleteFoodToMeal(Long mealId, Long foodId) {
        if (!foodToMealRepository.existsByMealIdAndFoodId(mealId, foodId)) {
            throw new ResourceNotFoundException(
                    "Food", "id", String.valueOf(foodId), "Meal", "id", String.valueOf(mealId));
        }

        foodToMealRepository.deleteFoodToMealByMeal_IdAndFood_Id(mealId, foodId);
    }
}
