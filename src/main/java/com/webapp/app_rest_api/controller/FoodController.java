package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.FoodFacade;
import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.model.entities.Food;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodFacade foodFacade;

    public FoodController(FoodFacade foodFacade) {
        this.foodFacade = foodFacade;
    }

    @GetMapping("/{id}")
    public FoodDto getFoodById(@PathVariable long id){
        return foodFacade.getFoodById(id);
    }

    @GetMapping
    public List<FoodDto> getAllFood(){
        return foodFacade.getAllFood();
    }

    @GetMapping("/{id}/weight/{weight}")
    public FoodDto getFoodWithGivenWeight(@PathVariable long id, @PathVariable double weight){
        return foodFacade.getFoodWithGivenWeight(id, weight);
    }

    @PostMapping
    public FoodDto postFood(@RequestBody Food food){
        return foodFacade.createFood(food);
    }

    @PutMapping("/{id}")
    public FoodDto updateFood(@PathVariable long id, @RequestBody Food food){
        return foodFacade.updateFood(id, food);
    }

    @DeleteMapping("/{id}")
    public FoodDto deleteFood(@PathVariable long id){
        System.out.println("The food with id " + id + " was deleted.");
        return foodFacade.deleteFood(id);
    }
}
