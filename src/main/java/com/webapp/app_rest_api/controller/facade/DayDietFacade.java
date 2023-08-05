package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.DayDietDto;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.model.mapper.DayDietMapper;
import com.webapp.app_rest_api.service.impl.DayDietService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DayDietFacade {
    private final DayDietService dayDietService;
    private final DayDietMapper dayDietMapper;

    public DayDietFacade(DayDietService dayDietService,
                         DayDietMapper dayDietMapper) {
        this.dayDietService = dayDietService;
        this.dayDietMapper = dayDietMapper;
    }

    public DayDietDto createUpdateDayDiet(User user, DayDietDto dayDietDto) {
        DayDiet dayDiet = dayDietMapper.mapToEntity(dayDietDto);
        return dayDietMapper.mapToDto(dayDietService.createUpdateDayDiet(user.getPersonalInfo(), dayDiet));
    }

    public List<DayDietDto> getAllDayDietsByPersonalInfo(User user) {
        return dayDietService.getAllDayDietsByPersonalInfo(user.getPersonalInfo()).stream()
                .map(dayDietMapper::mapToDto)
                .toList();
    }

    public DayDietDto getDietDay(User user, Long id) {
        return dayDietMapper.mapToDto(dayDietService.getDayDietForUser(user.getPersonalInfo(), id));
    }

    public DayDietDto addMealToDietDay(User user, Long dietDayId, Long mealId) {
        return dayDietMapper.mapToDto(dayDietService.addMealToDayDiet(user.getPersonalInfo(), dietDayId, mealId));
    }

    public DayDietDto removeMealFromDietDay(User user, Long dietDayId, Long mealId) {
        return dayDietMapper.mapToDto(dayDietService.removeMealFromDayDiet(user.getPersonalInfo(), dietDayId, mealId));
    }

    public DayDietDto deleteDietDay(User user, Long id) {
        return dayDietMapper.mapToDto(dayDietService.deleteDayDiet(user.getPersonalInfo(), id));
    }
}
