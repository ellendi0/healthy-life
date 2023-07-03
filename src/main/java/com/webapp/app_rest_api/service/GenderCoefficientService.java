package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.entities.Role;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.model.enums.Gender;
import com.webapp.app_rest_api.model.mapper.GenderCoefficientMapper;
import com.webapp.app_rest_api.repository.GenderCoefficientRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GenderCoefficientService {
    private GenderCoefficientRepository genderCoefficientRepository;
    private GenderCoefficientMapper genderCoefficientMapper;

    public GenderCoefficientService(GenderCoefficientRepository genderCoefficientRepository) {
        this.genderCoefficientRepository = genderCoefficientRepository;
    }

    public GenderCoefficient createGenderCoefficient(GenderCoefficient genderCoefficient){
        return genderCoefficientRepository.save(genderCoefficient);
    }

    public GenderCoefficient getGenderCoefficientByGender(Gender gender){
        return genderCoefficientRepository.getGenderCoefficientByGender(gender).orElseThrow(() ->
                new ResourceNotFoundException("Gender coefficient", "gender", gender.name()));
    }

    public GenderCoefficient getGenderCoefficientById(Long id){
        return genderCoefficientRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Gender coefficient", "id", String.valueOf(id)));
    }

    public GenderCoefficient updateGenderCoefficient(Long id, GenderCoefficient genderCoefficient){
        return genderCoefficientRepository.save(genderCoefficientMapper
                .map(genderCoefficient, getGenderCoefficientById(id)));
    }

    public GenderCoefficient deleteGenderCoefficient(Long id){
        GenderCoefficient genderCoefficient = getGenderCoefficientById(id);
        genderCoefficientRepository.deleteById(id);
        return genderCoefficient;
    }
}
