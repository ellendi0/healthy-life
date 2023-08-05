package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.mapper.FoodMapper;
import com.webapp.app_rest_api.service.impl.FoodService;
import org.springframework.stereotype.Component;

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

    public FoodDto createFood(FoodDto foodDto) {
        return foodMapper.mapToDto(foodService.createFood(foodMapper.mapToEntity(foodDto)));
    }

    public FoodDto updateFood(Long id, FoodDto foodDto) {
        return foodMapper.mapToDto(foodService.updateFood(id, foodMapper.mapToEntity(foodDto)));
    }

    public FoodDto deleteFood(Long id) {
        FoodDto foodDto = foodMapper.mapToDto(foodService.getFoodById(id));
        foodService.deleteFood(id);
        System.out.println("The food with id " + id + " was deleted.");
        return foodDto;
    }

    public FoodDto getFoodWithGivenWeight(Long id, Double weight) {
        Food food = foodService.getFoodById(id);
        return foodMapper.mapToDto(food, weight);
    }
}
