package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.service.IDayDietScheduledService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayDietScheduledService implements IDayDietScheduledService {

    private final PersonalInfoService personalInfoService;
    private final DayDietService dayDietService;

    public DayDietScheduledService(
            PersonalInfoService personalInfoService,
            DayDietService dayDietService) {
        this.personalInfoService = personalInfoService;
        this.dayDietService = dayDietService;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void generateDayDiet() {
        List<PersonalInfo> personalInfo = personalInfoService.getAllUserAccountNonLocked();
        personalInfo.forEach(dayDietService::createUpdateDayDiet);
    }
}
