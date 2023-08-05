package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.DayDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayDietRepository extends JpaRepository<DayDiet, Long> {
    DayDiet findFirstByPersonalInfo_IdOrderByDate(Long id);
    Boolean existsByPersonalInfo_IdAndId(Long personalInfoId, Long id);

//    @Query("SELECT day_diet FROM DayDiet day_diet JOIN Meal meal ON day_diet.id = meal.dayDiet.id WHERE meal.id = :mealId AND day_diet.personalInfo.id = :personalInfoId")
//    Optional<DayDiet> findDayDietByMealId(@Param("mealId") Long mealId, @Param("personalInfoId") Long personalInfoId);

    Optional<DayDiet> findDayDietByIdAndPersonalInfo_Id(Long id, Long personalInfoId);
    Optional<DayDiet> findAllByPersonalInfo_IdOrderByDate(Long personalInfoId);

}
