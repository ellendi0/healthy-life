package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.service.IFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/food")
public class FoodController {

    private final IFoodService iFoodService;

    public FoodController(IFoodService iFoodService) {
        this.iFoodService = iFoodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDto> getFoodById(@PathVariable long id){
        return new ResponseEntity<>(iFoodService.getFoodById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FoodDto>> getAllFood(){
        return new ResponseEntity<>(iFoodService.getAllFood(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> postFood(@RequestBody Food food){
        return new ResponseEntity<>(iFoodService.createFood(food), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable long id, @RequestBody Food food){
        return new ResponseEntity<>(iFoodService.updateFood(id, food), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable long id){
        iFoodService.deleteFood(id);
        return new ResponseEntity<>("The food is successfully deleted", HttpStatus.OK);
    }

}
