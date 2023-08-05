package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import com.webapp.app_rest_api.repository.DayDietRepository;
import com.webapp.app_rest_api.service.IDayDietService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayDietService implements IDayDietService {
    private final DayDietRepository dayDietRepository;
    private final MealService mealService;

    public DayDietService(DayDietRepository dayDietRepository,
                          MealService mealService) {
        this.dayDietRepository = dayDietRepository;
        this.mealService = mealService;
    }

    @Override
    public DayDiet createUpdateDayDiet(PersonalInfo personalInfo, DayDiet dayDiet) {
        dayDiet.setPersonalInfo(personalInfo);
        return dayDietRepository.save(dayDiet);
    }

    @Override
    public DayDiet createUpdateDayDiet(PersonalInfo personalInfo) {
        DayDiet dayDiet = new DayDiet();
        dayDiet.setPersonalInfo(personalInfo);
        dayDiet.setDate(java.time.LocalDate.now());
        dayDietRepository.save(dayDiet);
        dayDiet.getMeals().add(mealService.createSaveMeal(dayDiet, TypeOfMeal.BREAKFAST));
        dayDiet.getMeals().add(mealService.createSaveMeal(dayDiet, TypeOfMeal.LUNCH));
        dayDiet.getMeals().add(mealService.createSaveMeal(dayDiet, TypeOfMeal.EVENING_SNACKS));
        dayDiet.getMeals().add(mealService.createSaveMeal(dayDiet, TypeOfMeal.DINNER));
        return dayDietRepository.save(dayDiet);
    }

    @Override
    public List<DayDiet> getAllDayDietsByPersonalInfo(PersonalInfo personalInfo) {
        return dayDietRepository.findAllByPersonalInfo_IdOrderByDate(personalInfo.getId()).stream().toList();
    }

    @Override
    public DayDiet getDayDietForUser(PersonalInfo personalInfo, Long id) {
        return dayDietRepository.findDayDietByIdAndPersonalInfo_Id(id, personalInfo.getId())
                .orElseThrow(() -> new ResourceNotFoundException("The Day Diet", "id", String.valueOf(id)));
    }

//    @Override
//    public List<Meal> getAllMealsByDayDiet(PersonalInfo personalInfo, Long id) {
//        DayDiet dayDiet = getDayDietForUser(personalInfo, id);
//        return mealService.getAllMealsByDayDiet(dayDietRepository.findByPersonalInfo_IdAndId(personalInfo.getId(), id));
//    }

    @Override
    public DayDiet addMealToDayDiet(PersonalInfo personalInfo, Long dietDayId, Long mealId) {
        DayDiet dayDiet = getDayDietForUser(personalInfo, dietDayId);
        dayDiet.getMeals().add(mealService.getMealByUserAndMealId(personalInfo, mealId));
        dayDietRepository.save(dayDiet);
        return countMealsNutrition(dietDayId);

    }

    @Override
    public DayDiet removeMealFromDayDiet(PersonalInfo personalInfo, Long dietDayId, Long mealId) {
        DayDiet dayDiet = getDayDietForUser(personalInfo, dietDayId);
        dayDiet.getMeals().remove(mealService.getMealByUserAndMealId(personalInfo, mealId));
        System.out.println("The meal with id " +
                mealId + " has been removed from the diet day with id " + dietDayId + ".");
        dayDietRepository.save(dayDiet);
        return countMealsNutrition(dietDayId);
    }

    @Override
    public DayDiet deleteDayDiet(PersonalInfo personalInfo, Long id) {
        DayDiet dayDiet = getDayDietForUser(personalInfo, id);
        dayDietRepository.deleteById(id);
        System.out.println("The diet day with id " + id + " has been deleted.");
        return dayDiet;
    }

    public DayDiet countMealsNutrition(Long id) {
        DayDiet dayDiet = dayDietRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Diet Day", "id", String.valueOf(id)));
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
