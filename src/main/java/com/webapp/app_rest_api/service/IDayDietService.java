package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import com.webapp.app_rest_api.repository.DayDietRepository;
import com.webapp.app_rest_api.service.impl.MealService;

public interface IDayDietService {
    DayDiet createUpdateDayDiet(DayDiet dayDiet);

    DayDiet createUpdateDayDiet();

    DayDiet getDietDay(Long id);

    DayDiet addMealToDietDay(Long dietDayId, Long mealId);

    DayDiet removeMealFromDietDay(Long dietDayId, Long mealId);

    DayDiet deleteDietDay(Long id);
}
