package com.webapp.app_rest_api.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.webapp.app_rest_api.dto.FoodDto;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.mapper.FoodMapper;
import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.Meal;
import com.webapp.app_rest_api.model.enums.TypeOfFood;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.service.IFoodService;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class FoodService implements IFoodService {

    private FoodRepository foodRepository;
    private FoodMapper mapper;

    public FoodService(FoodRepository foodRepository, FoodMapper mapper) {
        this.foodRepository = foodRepository;
        this.mapper = mapper;
    }

    @Override
    public FoodDto getFoodById(long id) {
        return mapper.mapToDto(foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Food", "id", String.valueOf(id))));
    }

    @Override
    public List<FoodDto> getAllFood() {
        return foodRepository.findAll().stream().map(mapper::mapToDto).toList();
    }

    @Override
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(long id, Food food) {
        Food foodUpdated = foodRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
        foodUpdated.setName(foodUpdated.getName());
        foodUpdated.setTypeOfFood(foodUpdated.getTypeOfFood());
        foodUpdated.setNumberOfFat(foodUpdated.getNumberOfFat());
        foodUpdated.setNumberOfCarbohydrate(foodUpdated.getNumberOfCarbohydrate());
        foodUpdated.setNumberOfProtein(foodUpdated.getNumberOfProtein());
        foodUpdated.setNumberOfCalories(foodUpdated.getNumberOfCalories());
        foodUpdated.setNumberOfSugar(foodUpdated.getNumberOfSugar());
        foodUpdated.setNumberOfFiber(foodUpdated.getNumberOfFiber());
        return foodRepository.save(foodUpdated);
    }

    @Override
    public void deleteFood(long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodDto getFoodWithGivenWeight(long id, double weight) {
        Food food = foodRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
        FoodDto foodWithGivenWeight = mapper.mapToDto(food);
        foodWithGivenWeight.setId(food.getId());
        foodWithGivenWeight.setName(food.getName());
        foodWithGivenWeight.setTypeOfFood(food.getTypeOfFood());
        foodWithGivenWeight.setWeight(weight);
        foodWithGivenWeight.setNumberOfCalories(DoubleRounder.round(food.getNumberOfCalories() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfFat(DoubleRounder.round(food.getNumberOfFat() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfCarbohydrate(DoubleRounder.round(food.getNumberOfCarbohydrate() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfProtein(DoubleRounder.round(food.getNumberOfProtein() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfSugar(DoubleRounder.round(food.getNumberOfSugar() * weight / 100, 3));
        foodWithGivenWeight.setNumberOfFiber(DoubleRounder.round(food.getNumberOfFiber() * weight / 100, 3));
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
