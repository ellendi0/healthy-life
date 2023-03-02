package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FoodService implements IFoodService{

    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food getFoodById(long id) {
        return foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Аood", "id", String.valueOf(id)));
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(long id, Food food) {
        Food updatedFood = foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
        updatedFood.setName(food.getName());
        updatedFood.setWeight(food.getWeight());
        updatedFood.setNumberOfFat(food.getNumberOfFat());
        updatedFood.setNumberOfCarbohydrate(food.getNumberOfCarbohydrate());
        updatedFood.setNumberOfProtein(food.getNumberOfProtein());
        updatedFood.setNumberOfCalories(food.getNumberOfCalories());

        return foodRepository.save(updatedFood);
    }

    @Override
    public void deleteFood(long id) {
        foodRepository.deleteById(id);
    }
}
