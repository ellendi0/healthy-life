package com.webapp.app_rest_api.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.mapper.FoodMapper;
import com.webapp.app_rest_api.model.entities.Food;
import com.webapp.app_rest_api.model.enums.TypeOfFood;
import com.webapp.app_rest_api.repository.FoodRepository;
import com.webapp.app_rest_api.service.IFoodService;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class FoodService implements IFoodService {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
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
    public Food updateFood(Long id, Food food) {
        return foodRepository.findById(id).map(food1 -> foodMapper.mapFood(food)).map(foodRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Food", "id", String.valueOf(id)));
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
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
