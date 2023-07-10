package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.PersonalInfoFacade;
import com.webapp.app_rest_api.dto.PersonalInfoDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/personalinfo")
public class PersonalInfoController {
    private final PersonalInfoFacade personalInfoFacade;

    public PersonalInfoController(PersonalInfoFacade personalInfoFacade) {
        this.personalInfoFacade = personalInfoFacade;
    }

    @PostMapping
    public PersonalInfoDto createPersonalInfo(@Valid @RequestBody PersonalInfoDto personalInfoDto) {
        return personalInfoFacade.createPersonalInfo(personalInfoDto);
    }

    @GetMapping("/{id}")
    public PersonalInfoDto getPersonalInfoById(@PathVariable Long id) {
        return personalInfoFacade.getPersonalInfoById(id);
    }

    @PutMapping("/{id}")
    public PersonalInfoDto updatePersonalInfo(@Valid @PathVariable Long id, @RequestBody PersonalInfo personalInfo) {
        return personalInfoFacade.updatePersonalInfo(id, personalInfo);
    }

    @DeleteMapping("/{id}")
    public PersonalInfoDto deletePersonalInfo(@PathVariable Long id) {
        System.out.println("The personal info with id " + id + " has been deleted.");
        return personalInfoFacade.deletePersonalInfo(id);
    }

    @GetMapping("/user/{userId}")
    public PersonalInfoDto getPersonalInfoByUserId(@PathVariable Long userId) {
        return personalInfoFacade.getPersonalInfoByUserId(userId);
    }
}
