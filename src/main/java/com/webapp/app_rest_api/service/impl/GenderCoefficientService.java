package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.mapper.GenderCoefficientMapper;
import com.webapp.app_rest_api.repository.GenderCoefficientRepository;
import com.webapp.app_rest_api.service.IGenderCoefficientService;
import org.springframework.stereotype.Service;

@Service
public class GenderCoefficientService implements IGenderCoefficientService {
    private final GenderCoefficientRepository genderCoefficientRepository;
    private final GenderCoefficientMapper genderCoefficientMapper;

    public GenderCoefficientService(GenderCoefficientRepository genderCoefficientRepository,
                                    GenderCoefficientMapper genderCoefficientMapper) {
        this.genderCoefficientRepository = genderCoefficientRepository;
        this.genderCoefficientMapper = genderCoefficientMapper;
    }

    @Override
    public GenderCoefficient createGenderCoefficient(GenderCoefficient genderCoefficient) {
        return genderCoefficientRepository.save(genderCoefficient);
    }

    @Override
    public GenderCoefficient getGenderCoefficientByGender(Gender gender) {
        return genderCoefficientRepository.getGenderCoefficientByGender(gender).orElseThrow(() ->
                new ResourceNotFoundException("Gender coefficient", "gender", gender.name()));
    }

    @Override
    public GenderCoefficient getGenderCoefficientById(Long id) {
        return genderCoefficientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Gender coefficient", "id", String.valueOf(id)));
    }

    @Override
    public GenderCoefficient updateGenderCoefficient(Long id, GenderCoefficient genderCoefficient) {
        return genderCoefficientRepository.save(genderCoefficientMapper
                .map(genderCoefficient, getGenderCoefficientById(id)));
    }

    @Override
    public GenderCoefficient deleteGenderCoefficient(Long id) {
        GenderCoefficient genderCoefficient = getGenderCoefficientById(id);
        genderCoefficientRepository.deleteById(id);
        return genderCoefficient;
    }
}
