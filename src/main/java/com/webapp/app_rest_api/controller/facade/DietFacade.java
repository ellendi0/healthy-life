package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.DietDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.mapper.DietMapper;
import com.webapp.app_rest_api.service.impl.DietService;
import org.springframework.stereotype.Component;

@Component
public class DietFacade {
    private final DietService dietService;
    private final DietMapper dietMapper;

    public DietFacade(DietService dietService, DietMapper dietMapper) {
        this.dietService = dietService;
        this.dietMapper = dietMapper;
    }

    public DietDto createDiet(PersonalInfo personalInfo){
        return dietMapper.mapToDto(dietService.createDiet(personalInfo));
    }

    public DietDto createUpdateDiet(DietDto dietDto){
        return dietMapper.mapToDto(dietService.createUpdateDiet(dietMapper.mapToEntity(dietDto)));
    }

    public DietDto getDietById(Long id) {
        return dietMapper.mapToDto(dietService.getDietById(id));
    }

    public DietDto updateDiet(Long id, Double calories){
        return dietMapper.mapToDto(dietService.updateDiet(id, calories));
    }

    public DietDto deleteDiet(Long id){
        DietDto dietDto = getDietById(id);
        dietService.deleteDiet(id);
        return dietDto;
    }
}
