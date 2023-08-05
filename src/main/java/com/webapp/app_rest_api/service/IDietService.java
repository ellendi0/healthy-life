package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.Diet;
import com.webapp.app_rest_api.model.entities.PersonalInfo;

public interface IDietService {
    Diet createCustomDiet(PersonalInfo personalInfo);
    Diet createCustomDiet(PersonalInfo personalInfo, Diet diet);
    Diet getDietById(Long id);
    Diet updateDiet(PersonalInfo personalInfo, Double calories);
    Diet getDietByUser(PersonalInfo personalInfo);
    Diet deleteDiet(Long id);
}
