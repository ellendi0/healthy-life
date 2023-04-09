package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.Food;

import java.util.List;

public interface IFoodService {
    Food getFoodById(long id);
    List<Food> getAllFood();
    Food createFood(Food food);
    Food updateFood(long id, Food food);
    void deleteFood(long id);

    Food getFoodWithGivenWeight(long id, double weight);
}
