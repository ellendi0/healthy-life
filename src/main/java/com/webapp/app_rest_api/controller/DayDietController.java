package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.DayDietFacade;
import com.webapp.app_rest_api.dto.DayDietDto;
import com.webapp.app_rest_api.model.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/dayDiet")
public class DayDietController {
    private final DayDietFacade dayDietFacade;

    public DayDietController(DayDietFacade dayDietFacade) {
        this.dayDietFacade = dayDietFacade;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
        public DayDietDto createUpdateDayDiet(@AuthenticationPrincipal User user,
                                              @RequestBody DayDietDto dayDietDto) {
        return dayDietFacade.createUpdateDayDiet(user, dayDietDto);
    }

    @GetMapping("{id}")
    public DayDietDto getDayDiet(@AuthenticationPrincipal User user,
                                 @RequestParam(value = "id") Long id) {
        return dayDietFacade.getDietDay(user, id);
    }

    @GetMapping("/all")
    public List<DayDietDto> getAllDayDiet(@AuthenticationPrincipal User user) {
        return dayDietFacade.getAllDayDietsByPersonalInfo(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("{dayDietId}/meal/{mealId}")
    public DayDietDto addMealToDayDiet(@AuthenticationPrincipal User user,
                                       @PathVariable(value = "dayDietId") Long dietDayId,
                                       @PathVariable(value = "mealId") Long mealId){
        return dayDietFacade.addMealToDietDay(user, dietDayId, mealId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{dayDietId}/meal/{mealId}")
    public DayDietDto removeMealFromDayDiet(@AuthenticationPrincipal User user,
                                            @PathVariable(value = "dayDietId") Long dietDayId,
                                            @PathVariable(value = "mealId") Long mealId){
        return dayDietFacade.removeMealFromDietDay(user, dietDayId, mealId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public DayDietDto deleteDayDiet(@AuthenticationPrincipal User user,
                                    @PathVariable Long id) {
        return dayDietFacade.deleteDietDay(user, id);
    }
}
