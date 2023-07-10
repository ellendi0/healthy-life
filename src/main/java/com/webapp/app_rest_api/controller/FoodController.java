package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.FoodFacade;
import com.webapp.app_rest_api.dto.FoodDto;
import jakarta.validation.Valid;
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
    public FoodDto getFoodById(@PathVariable Long id){
        return foodFacade.getFoodById(id);
    }

    @GetMapping
    public List<FoodDto> getAllFood(){
        return foodFacade.getAllFood();
    }

    @GetMapping("/{id}/weight/{weight}")
    public FoodDto getFoodWithGivenWeight(@PathVariable Long id, @PathVariable Double weight){
        return foodFacade.getFoodWithGivenWeight(id, weight);
    }

    @PostMapping
    public FoodDto createFood(@Valid @RequestBody FoodDto foodDto){
        return foodFacade.createFood(foodDto);
    }

    @PutMapping("/{id}")
    public FoodDto updateFood(@Valid @PathVariable Long id, @RequestBody FoodDto foodDto){
        return foodFacade.updateFood(id, foodDto);
    }

    @DeleteMapping("/{id}")
    public FoodDto deleteFood(@PathVariable Long id){
        System.out.println("The food with id " + id + " was deleted.");
        return foodFacade.deleteFood(id);
    }
}
