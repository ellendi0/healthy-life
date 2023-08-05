package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.Meal;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.enums.TypeOfMeal;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findMealByDayDietAndTypeOfMeal(DayDiet dayDiet, TypeOfMeal typeOfMeal);
    List<Meal> findAllByDayDiet(DayDiet dayDiet);
    @Query("SELECT meal from Meal meal join DayDiet dayDiet on dayDiet.id = meal.dayDiet.id and dayDiet.personalInfo.id = :personalInfoId and meal.id = :mealId")
    Optional<Meal> findMealByPersonalInfo(@Param(value = "personalInfoId") Long personalInfoId,
                                          @Param(value = "mealId") Long mealId);

}