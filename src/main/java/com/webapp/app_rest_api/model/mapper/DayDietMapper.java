package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.DayDietDto;
import com.webapp.app_rest_api.dto.MealDto;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.Meal;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DayDietMapper {
    private final MealMapper mealMapper;

    public DayDietMapper(MealMapper mealMapper) {
        this.mealMapper = mealMapper;
    }

    public DayDietDto mapToDto(DayDiet dayDiet){
        DayDietDto dayDietDto = new DayDietDto();
        dayDietDto.setDate(dayDiet.getDate());
        dayDietDto.setTotalNumberOfDailyCalories(dayDiet.getTotalNumberOfDailyCalories());
        dayDietDto.setTotalNumberOfProtein(dayDiet.getTotalNumberOfProtein());
        dayDietDto.setTotalNumberOfCarbohydrate(dayDiet.getTotalNumberOfCarbohydrate());
        dayDietDto.setTotalNumberOfFat(dayDiet.getTotalNumberOfFat());
        dayDietDto.setTotalNumberOfFiber(dayDiet.getTotalNumberOfFiber());
        dayDietDto.setTotalNumberOfSugar(dayDiet.getTotalNumberOfSugar());
        dayDietDto.setMeals(mapToMealDtoList(dayDiet.getMeals()));
        return dayDietDto;
    }

    public DayDiet mapToEntity(DayDietDto dayDietDto){
        DayDiet dayDiet = new DayDiet();
        dayDiet.setDate(LocalDate.now());
        dayDiet.setTotalNumberOfDailyCalories(dayDietDto.getTotalNumberOfDailyCalories());
        dayDiet.setTotalNumberOfProtein(dayDietDto.getTotalNumberOfProtein());
        dayDiet.setTotalNumberOfCarbohydrate(dayDietDto.getTotalNumberOfCarbohydrate());
        dayDiet.setTotalNumberOfFat(dayDietDto.getTotalNumberOfFat());
        dayDiet.setTotalNumberOfFiber(dayDietDto.getTotalNumberOfFiber());
        dayDiet.setTotalNumberOfSugar(dayDietDto.getTotalNumberOfSugar());
        return dayDiet;
    }
    public List<MealDto> mapToMealDtoList(List<Meal> meal){
        return new ArrayList<>(meal.stream().map(mealMapper::mapToDto).toList());
    }

}
