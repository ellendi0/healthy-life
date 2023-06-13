package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.Food;
import com.webapp.app_rest_api.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}