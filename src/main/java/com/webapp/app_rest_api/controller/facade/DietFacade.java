package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.DietDto;
import com.webapp.app_rest_api.model.mapper.DietMapper;
import com.webapp.app_rest_api.service.impl.DietService;
import com.webapp.app_rest_api.service.impl.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class DietFacade {
    private final DietService dietService;
    private final DietMapper dietMapper;
    private final UserService userService;

    public DietFacade(DietService dietService, DietMapper dietMapper, UserService userService) {
        this.dietService = dietService;
        this.dietMapper = dietMapper;
        this.userService = userService;
    }

    public DietDto createDiet(UserDetails userDetails) {
        return dietMapper.mapToDto(dietService.createCustomDiet(userService.findByEmailOrUsername(
                userDetails.getUsername()).getPersonalInfo()));
    }

    public DietDto createDiet(UserDetails userDetails, DietDto dietDto) {
        return dietMapper.mapToDto(dietService.createCustomDiet(userService.findByEmailOrUsername(
                userDetails.getUsername()).getPersonalInfo(), dietMapper.mapToEntity(dietDto)));
    }

    public DietDto getDiet(UserDetails userDetails) {
        return dietMapper.mapToDto(dietService.getDietByUser(userService.findByEmailOrUsername(
                userDetails.getUsername()).getPersonalInfo()));
    }

    public DietDto getDietById(Long id) {
        return dietMapper.mapToDto(dietService.getDietById(id));
    }

    public DietDto updateDiet(UserDetails userDetails, Double calories) {
        return dietMapper.mapToDto(dietService.updateDiet(userService.findByEmailOrUsername(
                userDetails.getUsername()).getPersonalInfo(), calories));
    }

    public DietDto deleteDiet(Long id) {
        DietDto dietDto = getDietById(id);
        dietService.deleteDiet(id);
        System.out.println("The diet with id " + id + " has been deleted.");
        return dietDto;
    }
}
