package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.DayDietDto;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.mapper.DayDietMapper;
import com.webapp.app_rest_api.service.impl.DayDietService;
import org.springframework.stereotype.Component;

@Component
public class DayDietFacade {
    private final DayDietService dayDietService;
    private final DayDietMapper dayDietMapper;

    public DayDietFacade(DayDietService dayDietService, DayDietMapper dayDietMapper) {
        this.dayDietService = dayDietService;
        this.dayDietMapper = dayDietMapper;
    }

    public DayDietDto createUpdateDayDiet(DayDietDto dayDietDto) {
        DayDiet dayDiet = dayDietMapper.mapToEntity(dayDietDto);
        return dayDietMapper.mapToDto(dayDietService.createUpdateDayDiet(dayDiet));
    }

    public DayDietDto getDietDay(Long id) {
        return dayDietMapper.mapToDto(dayDietService.getDietDay(id));
    }

    public DayDietDto addMealToDietDay(Long dietDayId, Long mealId){
        return dayDietMapper.mapToDto(dayDietService.addMealToDietDay(dietDayId, mealId));
    }

    public DayDietDto removeMealFromDietDay(Long dietDayId, Long mealId){
        return dayDietMapper.mapToDto(dayDietService.removeMealFromDietDay(dietDayId, mealId));
    }

    public DayDietDto deleteDietDay(Long id) {
        return dayDietMapper.mapToDto(dayDietService.deleteDietDay(id));
    }
}
