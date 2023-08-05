package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.enums.Gender;

public interface IGenderCoefficientService {
    GenderCoefficient createGenderCoefficient(GenderCoefficient genderCoefficient);
    GenderCoefficient getGenderCoefficientByGender(Gender gender);
    GenderCoefficient getGenderCoefficientById(Long id);
    GenderCoefficient updateGenderCoefficient(Long id, GenderCoefficient genderCoefficient);
    GenderCoefficient deleteGenderCoefficient(Long id);
}
