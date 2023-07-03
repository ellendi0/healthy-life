package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.model.entities.Diet;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;

@Component
public class DietMapper {
    public Diet map(Diet dietToUpdate, double bmr){
        dietToUpdate.setNumberOfDailyCalories(DoubleRounder.round(bmr, 3));
        dietToUpdate.setNumberOfProtein(DoubleRounder.round(dietToUpdate.getNumberOfDailyCalories() * 0.25/4, 3));
        dietToUpdate.setNumberOfFat(DoubleRounder.round(dietToUpdate.getNumberOfDailyCalories() * 0.3/9, 3));
        dietToUpdate.setNumberOfCarbohydrate(DoubleRounder.round(dietToUpdate.getNumberOfDailyCalories() * 0.45/4, 3));
        dietToUpdate.setNumberOfSugar(DoubleRounder.round(dietToUpdate.getNumberOfDailyCalories() * 0.1/4, 3));
        dietToUpdate.setNumberOfFiber(DoubleRounder.round(dietToUpdate.getNumberOfDailyCalories() / 1000 * 14, 3));
        return dietToUpdate;
    }
}
