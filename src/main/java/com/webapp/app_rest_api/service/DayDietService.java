package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import com.webapp.app_rest_api.repository.DayDietRepository;
import org.springframework.stereotype.Service;

@Service
public class DayDietService {
    private final DayDietRepository dayDietRepository;
    private final MealService mealService;

    public DayDietService(DayDietRepository dayDietRepository, MealService mealService) {
        this.dayDietRepository = dayDietRepository;
        this.mealService = mealService;
    }


    public DayDiet createUpdateDayDiet(DayDiet dayDiet) {
        return dayDietRepository.save(dayDiet);
    }
    public DayDiet createUpdateDayDiet() {
        DayDiet dayDiet = new DayDiet();
        dayDiet.setDate(java.time.LocalDate.now());
        dayDiet.getMeals().add(mealService.createMeal(TypeOfMeal.BREAKFAST));
        dayDiet.getMeals().add(mealService.createMeal(TypeOfMeal.LUNCH));
        dayDiet.getMeals().add(mealService.createMeal(TypeOfMeal.EVENING_SNACKS));
        dayDiet.getMeals().add(mealService.createMeal(TypeOfMeal.DINNER));
        return dayDietRepository.save(dayDiet);
    }

    public DayDiet getDietDay(Long id) {
        return dayDietRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DietDay", "id", String.valueOf(id)));
    }

    public DayDiet addMealToDietDay(Long dietDayId, Long mealId){
        DayDiet dayDiet = getDietDay(dietDayId);
        dayDiet.getMeals().add(mealService.getMeal(mealId));
        dayDietRepository.save(dayDiet);
        return countMealsNutrition(dietDayId);

    }

    public DayDiet removeMealFromDietDay(Long dietDayId, Long mealId){
        DayDiet dayDiet = getDietDay(dietDayId);
        dayDiet.getMeals().remove(mealService.getMeal(mealId));
        dayDietRepository.save(dayDiet);
        return countMealsNutrition(dietDayId);
    }

    public void deleteDietDay(Long id) {
        dayDietRepository.deleteById(id);
    }

    public DayDiet countMealsNutrition(Long id){
        DayDiet dayDiet = getDietDay(id);
        double totalNumberOfDailyCalories = 0;
        double totalNumberOfProtein = 0;
        double totalNumberOfFat = 0;
        double totalNumberOfCarbohydrate = 0;
        double totalNumberOfSugar = 0;
        double totalNumberOfFiber = 0;

        for (Meal meal : dayDiet.getMeals()) {
            totalNumberOfDailyCalories += meal.getNumberOfCalories();
            totalNumberOfProtein += meal.getNumberOfProtein();
            totalNumberOfFat += meal.getNumberOfFat();
            totalNumberOfCarbohydrate += meal.getNumberOfCarbohydrate();
            totalNumberOfSugar += meal.getNumberOfSugar();
            totalNumberOfFiber += meal.getNumberOfFiber();
        }

        dayDiet.setTotalNumberOfDailyCalories(totalNumberOfDailyCalories);
        dayDiet.setTotalNumberOfProtein(totalNumberOfProtein);
        dayDiet.setTotalNumberOfFat(totalNumberOfFat);
        dayDiet.setTotalNumberOfCarbohydrate(totalNumberOfCarbohydrate);
        dayDiet.setTotalNumberOfSugar(totalNumberOfSugar);
        dayDiet.setTotalNumberOfFiber(totalNumberOfFiber);

        return dayDietRepository.save(dayDiet);
    }
}
