package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFoodService {
    Food getFoodById(Long id);

    List<Food> getAllFood();

    Food createFood(Food food);

    Food updateFood(Long id, Food food);

    void deleteFood(Long id);
}
