package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.PersonalInfo;

import java.util.List;

public interface IDayDietService {
    DayDiet createUpdateDayDiet(PersonalInfo personalInfo, DayDiet dayDiet);
    DayDiet createUpdateDayDiet(PersonalInfo personalInfo);
    List<DayDiet> getAllDayDietsByPersonalInfo(PersonalInfo personalInfo);
    DayDiet getDayDietForUser(PersonalInfo personalInfo, Long id);
    DayDiet addMealToDayDiet(PersonalInfo personalInfo, Long dietDayId, Long mealId);
    DayDiet removeMealFromDayDiet(PersonalInfo personalInfo, Long dietDayId, Long mealId);
    DayDiet deleteDayDiet(PersonalInfo personalInfo, Long id);
}
