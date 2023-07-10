package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.DayDietFacade;
import com.webapp.app_rest_api.dto.DayDietDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/daydiet")
public class DayDietController {
    private final DayDietFacade dayDietFacade;

    public DayDietController(DayDietFacade dayDietFacade) {
        this.dayDietFacade = dayDietFacade;
    }

    @PostMapping
    public DayDietDto createUpdateDayDiet(@RequestBody DayDietDto dayDietDto) {
        return dayDietFacade.createUpdateDayDiet(dayDietDto);
    }

    @GetMapping("/{id}")
    public DayDietDto getDietDay(@PathVariable Long id) {
        return dayDietFacade.getDietDay(id);
    }

    @PutMapping("/{dietDayId}/meal/{mealId}")
    public DayDietDto addMealToDietDay(@PathVariable Long dietDayId, @PathVariable Long mealId){
        return dayDietFacade.addMealToDietDay(dietDayId, mealId);
    }

    @DeleteMapping("/{dietDayId}/meal/{mealId}")
    public DayDietDto removeMealFromDietDay(@PathVariable Long dietDayId, @PathVariable Long mealId){
        System.out.println("The meal with id " + mealId + " has been removed from the diet day with id " + dietDayId + ".");
        return dayDietFacade.removeMealFromDietDay(dietDayId, mealId);
    }

    @DeleteMapping("/{id}")
    public DayDietDto deleteDietDay(@PathVariable Long id) {
        System.out.println("The diet day with id " + id + " has been deleted.");
        return dayDietFacade.deleteDietDay(id);
    }
}
