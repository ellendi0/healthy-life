package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import org.springframework.stereotype.Component;

@Component
public class GenderCoefficientMapper {
    public GenderCoefficient map(GenderCoefficient genderCoefficient, GenderCoefficient genderCoefficientToUpdate) {
        genderCoefficientToUpdate.setBmr_base(genderCoefficient.getBmr_base());
        genderCoefficientToUpdate.setBmr_weight_coefficient(genderCoefficient.getBmr_weight_coefficient());
        genderCoefficientToUpdate.setBmr_height_coefficient(genderCoefficient.getBmr_height_coefficient());
        genderCoefficientToUpdate.setBmr_age_coefficient(genderCoefficient.getBmr_age_coefficient());
        return genderCoefficientToUpdate;
    }
}
