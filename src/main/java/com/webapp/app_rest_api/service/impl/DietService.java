package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.Diet;
import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.mapper.DietMapper;
import com.webapp.app_rest_api.repository.DietRepository;
import com.webapp.app_rest_api.service.IDietService;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DietService implements IDietService {

    private final DietRepository dietRepository;
    private final GenderCoefficientService genderCoefficientService;
    private final DietMapper mapper;

    public DietService(DietRepository dietRepository,
                       GenderCoefficientService genderCoefficientService,
                       DietMapper mapper) {
        this.dietRepository = dietRepository;
        this.genderCoefficientService = genderCoefficientService;
        this.mapper = mapper;
    }

    @Override
    public Diet createDiet(PersonalInfo personalInfo) {
        Diet diet = new Diet();
        personalInfo.setDiet(diet);
        diet.setPersonalInfo(personalInfo);
        return dietRepository.save(countDiet(personalInfo, diet));
    }

    @Override
    public Diet createUpdateDiet(Diet diet) {
        return dietRepository.save(diet);
    }

    @Override
    public Diet getDietById(Long id) {
        return dietRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Diet", "id", String.valueOf(id)));
    }

    @Override
    public Diet updateDiet(Long id, Double calories) {
        Diet updatedDiet = getDietById(id);
        return dietRepository.save(mapper.map(updatedDiet, calories));
    }

    @Override
    public Diet deleteDiet(Long id) {
        Diet diet = getDietById(id);
        dietRepository.deleteById(id);
        return diet;
    }

    public Diet countDiet(PersonalInfo personalInfo, Diet diet) {
        double bmr;
        int currentYear = LocalDate.now().getYear();
        GenderCoefficient genderCoefficient = genderCoefficientService
                .getGenderCoefficientByGender(personalInfo.getGender());

        bmr = DoubleRounder.round(genderCoefficient.getBmr_base()
                + (genderCoefficient.getBmr_weight_coefficient() * personalInfo.getWeight())
                + (genderCoefficient.getBmr_height_coefficient() * personalInfo.getHeight())
                - (genderCoefficient.getBmr_age_coefficient()
                * (currentYear - personalInfo.getDateOfBirth().getYear())), 3);

        bmr = bmr * personalInfo.getActivity().getALF() * personalInfo.getGoal().getCaloriesByGoal();
        return dietRepository.save(mapper.map(diet, bmr));
    }
}
