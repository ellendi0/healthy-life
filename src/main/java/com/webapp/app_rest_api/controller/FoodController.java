package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.FoodFacade;
import com.webapp.app_rest_api.dto.FoodDto;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    private final FoodFacade foodFacade;

    public FoodController(FoodFacade foodFacade) {
        this.foodFacade = foodFacade;
    }

    @GetMapping("{id}")
    public FoodDto getFoodById(@PathVariable Long id){
        return foodFacade.getFoodById(id);
    }

    @GetMapping
    public List<FoodDto> getAllFood(){
        return foodFacade.getAllFood();
    }

    @GetMapping("{id}/")
    public FoodDto getFoodWithGivenWeight(@PathVariable Long id,
                                          @RequestParam(value = "weight") Double weight){
        return foodFacade.getFoodWithGivenWeight(id, weight);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public FoodDto createFood(@Valid @RequestBody FoodDto foodDto){
        return foodFacade.createFood(foodDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public FoodDto updateFood(@Valid @PathVariable Long id,
                              @RequestBody FoodDto foodDto){
        return foodFacade.updateFood(id, foodDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public FoodDto deleteFood(@PathVariable Long id){
        return foodFacade.deleteFood(id);
    }
}
