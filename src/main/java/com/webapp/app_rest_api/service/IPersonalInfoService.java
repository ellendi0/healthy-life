package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.DayDiet;
import com.webapp.app_rest_api.model.entities.PersonalInfo;

import java.util.List;

public interface IPersonalInfoService {
    PersonalInfo createPersonalInfo(PersonalInfo personalInfo);
    PersonalInfo getPersonalInfoById(Long id);

    List<PersonalInfo> getAllUserAccountNonLocked();

    PersonalInfo updatePersonalInfo(Long id, PersonalInfo personalInfo);

    PersonalInfo deletePersonalInfo(Long id);

    PersonalInfo getPersonalInfoByUserId(Long userId);
}
