package com.webapp.app_rest_api.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.enums.TypeOfFood;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.service.IFoodService;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class FoodService implements IFoodService {

    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food getFoodById(long id) {
        return foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(long id, Food food) {
        Food updatedFood = foodRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
        updatedFood.setName(food.getName());
        updatedFood.setTypeOfFood(food.getTypeOfFood());
        updatedFood.setNumberOfFat(food.getNumberOfFat());
        updatedFood.setNumberOfCarbohydrate(food.getNumberOfCarbohydrate());
        updatedFood.setNumberOfProtein(food.getNumberOfProtein());
        updatedFood.setNumberOfCalories(food.getNumberOfCalories());
        updatedFood.setNumberOfSugar(food.getNumberOfSugar());
        updatedFood.setNumberOfFiber(food.getNumberOfFiber());

        return foodRepository.save(updatedFood);
    }

    @Override
    public void deleteFood(long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public Food getFoodWithGivenWeight(long id, double weight) {
        Food food = foodRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
        Food foodWithGivenWeight = new Food();
        foodWithGivenWeight.setName(food.getName());
        foodWithGivenWeight.setTypeOfFood(food.getTypeOfFood());
        foodWithGivenWeight.setWeight(weight);
        foodWithGivenWeight.setNumberOfCalories(food.getNumberOfCalories() * weight / 100);
        foodWithGivenWeight.setNumberOfFat(food.getNumberOfFat() * weight / 100);
        foodWithGivenWeight.setNumberOfCarbohydrate(food.getNumberOfCarbohydrate() * weight / 100);
        foodWithGivenWeight.setNumberOfProtein(food.getNumberOfProtein() * weight / 100);
        foodWithGivenWeight.setNumberOfSugar(food.getNumberOfSugar() * weight / 100);
        foodWithGivenWeight.setNumberOfFiber(food.getNumberOfFiber() * weight / 100);
        return foodWithGivenWeight;
    }

    public void saveDataFromCsv(String csvFilePath) throws IOException, CsvValidationException {
        FileReader fileReader = new FileReader(csvFilePath);
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            Food food = new Food();
            food.setName(nextRecord[0]);
            food.setTypeOfFood(TypeOfFood.valueOf(nextRecord[1]));
            food.setNumberOfCalories(Double.parseDouble(nextRecord[2]));
            food.setNumberOfFat(Double.parseDouble(nextRecord[3]));
            food.setNumberOfProtein(Double.parseDouble(nextRecord[4]));
            food.setNumberOfCarbohydrate(Double.parseDouble(nextRecord[5]));
            food.setNumberOfSugar(Double.parseDouble(nextRecord[6]));
            food.setNumberOfFiber(Double.parseDouble(nextRecord[7]));
            food.setWeight(Double.parseDouble(nextRecord[8]));
            foodRepository.save(food);
        }

        csvReader.close();
        fileReader.close();
    }


}
