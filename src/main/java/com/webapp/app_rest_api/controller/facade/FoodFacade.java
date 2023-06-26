package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.mapper.FoodMapper;
import com.webapp.app_rest_api.service.impl.FoodService;
import org.decimal4j.util.DoubleRounder;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class FoodFacade {
    private final FoodService foodService;
    private final FoodMapper foodMapper;

    public FoodFacade(FoodService foodService, FoodMapper foodMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
    }

    public FoodDto getFoodById(Long id) {
        return foodMapper.mapToDto(foodService.getFoodById(id));
    }

    public List<FoodDto> getAllFood() {
        return foodService.getAllFood().stream()
                .map(foodMapper::mapToDto)
                .toList();
    }

    public FoodDto createFood(Food food) {
        return foodMapper.mapToDto(foodService.createFood(food));
    }

    public FoodDto updateFood(Long id, Food food) {
        return foodMapper.mapToDto(foodService.updateFood(id, food));
    }

    public FoodDto deleteFood(Long id) {
        FoodDto foodDto = foodMapper.mapToDto(foodService.getFoodById(id));
        foodService.deleteFood(id);
        return foodDto;
    }

    public FoodDto getFoodWithGivenWeight(Long id, Double weight) {
        Food food = foodService.getFoodById(id);
        FoodDto foodWithGivenWeight = foodMapper.mapToDto(food);
        foodWithGivenWeight.setName(food.getName());
        foodWithGivenWeight.setTypeOfFood(food.getTypeOfFood());
        foodWithGivenWeight.setWeight(weight);
        foodWithGivenWeight.setNumberOfCalories(DoubleRounder
                .round(food.getNumberOfCalories() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfFat(DoubleRounder
                .round(food.getNumberOfFat() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfCarbohydrate(DoubleRounder
                .round(food.getNumberOfCarbohydrate() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfProtein(DoubleRounder
                .round(food.getNumberOfProtein() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfSugar(DoubleRounder
                .round(food.getNumberOfSugar() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfFiber(DoubleRounder
                .round(food.getNumberOfFiber() * weight / 100, 3));
        return foodWithGivenWeight;
    }

}
