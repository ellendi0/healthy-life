package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.DietFacade;
import com.webapp.app_rest_api.dto.DietDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/diet")
public class DietController {
    private final DietFacade dietFacade;

    public DietController(DietFacade dietFacade) {
        this.dietFacade = dietFacade;
    }

    @PostMapping("/createpersonalinfo")
    public DietDto createDiet(@RequestBody PersonalInfo personalInfo){
        return dietFacade.createDiet(personalInfo);
    }

    @PostMapping
    public DietDto createUpdateDiet(@RequestBody DietDto dietDto){
        return dietFacade.createUpdateDiet(dietDto);
    }

    @GetMapping("/{id}")
    public DietDto getDietById(@PathVariable Long id) {
        return dietFacade.getDietById(id);
    }

    @PutMapping("/{id}/calories/{calories}")
    public DietDto updateDiet(@PathVariable Long id, @PathVariable Double calories){
        return dietFacade.updateDiet(id, calories);
    }

    @DeleteMapping("/{id}")
    public DietDto deleteDiet(@PathVariable Long id){
        System.out.println("The diet with id " + id + " has been deleted.");
        return dietFacade.deleteDiet(id);
    }
}
