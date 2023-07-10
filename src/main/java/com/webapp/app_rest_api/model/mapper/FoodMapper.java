package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.model.entities.Food;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;

//зробити свої
@Component
public class FoodMapper {
    public Food mapFood(Food food){
        Food newFood = new Food();
        newFood.setId(food.getId());
        newFood.setName(food.getName());
        newFood.setNumberOfCalories(food.getNumberOfCalories());
        newFood.setNumberOfCarbohydrate(food.getNumberOfCarbohydrate());
        newFood.setNumberOfFat(food.getNumberOfFat());
        newFood.setNumberOfProtein(food.getNumberOfProtein());
        newFood.setNumberOfSugar(food.getNumberOfSugar());
        newFood.setNumberOfFiber(food.getNumberOfFiber());
        newFood.setTypeOfFood(food.getTypeOfFood());
        return newFood;
    }

    public Food mapToEntity(FoodDto foodDto){
        Food food = new Food();
        food.setName(foodDto.getName());
        food.setNumberOfCalories(foodDto.getNumberOfCalories());
        food.setNumberOfCarbohydrate(foodDto.getNumberOfCarbohydrate());
        food.setNumberOfFat(foodDto.getNumberOfFat());
        food.setNumberOfProtein(foodDto.getNumberOfProtein());
        food.setNumberOfSugar(foodDto.getNumberOfSugar());
        food.setNumberOfFiber(foodDto.getNumberOfFiber());
        food.setTypeOfFood(foodDto.getTypeOfFood());
        return food;
    }

    public FoodDto mapToDto(Food food){
        FoodDto foodDto = new FoodDto();
        foodDto.setName(food.getName());
        foodDto.setWeight(food.getWeight());
        foodDto.setNumberOfCalories(food.getNumberOfCalories());
        foodDto.setNumberOfCarbohydrate(food.getNumberOfCarbohydrate());
        foodDto.setNumberOfFat(food.getNumberOfFat());
        foodDto.setNumberOfProtein(food.getNumberOfProtein());
        foodDto.setNumberOfSugar(food.getNumberOfSugar());
        foodDto.setNumberOfFiber(food.getNumberOfFiber());
        foodDto.setTypeOfFood(food.getTypeOfFood());
        return foodDto;
    }

    public FoodDto mapToDto(Food food, double weight){
        FoodDto foodDto = new FoodDto();
        foodDto.setName(food.getName());
        foodDto.setTypeOfFood(food.getTypeOfFood());
        foodDto.setWeight(weight);
        foodDto.setNumberOfCalories(DoubleRounder
                .round(food.getNumberOfCalories() * weight / 100, 3));
        foodDto.setNumberOfFat(DoubleRounder
                .round(food.getNumberOfFat() * weight / 100, 3));
        foodDto.setNumberOfCarbohydrate(DoubleRounder
                .round(food.getNumberOfCarbohydrate() * weight / 100, 3));
        foodDto.setNumberOfProtein(DoubleRounder
                .round(food.getNumberOfProtein() * weight / 100, 3));
        foodDto.setNumberOfSugar(DoubleRounder
                .round(food.getNumberOfSugar() * weight / 100, 3));
        foodDto.setNumberOfFiber(DoubleRounder
                .round(food.getNumberOfFiber() * weight / 100, 3));
        return foodDto;
    }
}
