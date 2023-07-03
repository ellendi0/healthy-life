package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.PersonalInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayDietScheduledService {

    private final UserService userService;
    private final PersonalInfoService personalInfoService;
    private final DayDietService dayDietService;

    public DayDietScheduledService(UserService userService,
                                   PersonalInfoService personalInfoService,
                                   DayDietService dayDietService) {
        this.userService = userService;
        this.personalInfoService = personalInfoService;
        this.dayDietService = dayDietService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void generateDayDiet() {
        List<PersonalInfo> personalInfo = personalInfoService.getAllUserAccountNonLocked();
        personalInfo.forEach(p -> p.getDayDiets().add(dayDietService.createUpdateDayDiet()));
    }
}
