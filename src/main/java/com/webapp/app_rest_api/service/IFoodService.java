package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.model.Food;

import java.util.List;

public interface IFoodService {
    FoodDto getFoodById(long id);
    List<FoodDto> getAllFood();
    Food createFood(Food food);
    Food updateFood(long id, Food food);
    void deleteFood(long id);
    FoodDto getFoodWithGivenWeight(long id, double weight);
}
