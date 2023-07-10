package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Diet;
import com.webapp.app_rest_api.model.entities.PersonalInfo;

public interface IDietService {
    Diet createDiet(PersonalInfo personalInfo);

    Diet createUpdateDiet(Diet diet);

    Diet getDietById(Long id);

    Diet updateDiet(Long id, Double calories);

    Diet deleteDiet(Long id);
}
