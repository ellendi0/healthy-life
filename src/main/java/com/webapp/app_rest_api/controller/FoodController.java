package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.service.impl.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/food")
public class FoodController {

    private final FoodService foodService;
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable long id){
        return foodService.getFoodById(id);
    }

    @GetMapping
    public List<Food> getAllFood(){
        return foodService.getAllFood();
    }

    @PostMapping
    public Food postFood(@RequestBody Food food){
        return foodService.createFood(food);
    }

    @PutMapping("/{id}")
    public Food updateFood(@PathVariable long id, @RequestBody Food food){
        return foodService.updateFood(id, food);
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable long id){
        foodService.deleteFood(id);
        return "Food with id " + id + " was deleted";
    }

}
