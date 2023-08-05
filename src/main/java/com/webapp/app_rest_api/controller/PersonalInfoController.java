package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.PersonalInfoFacade;
import com.webapp.app_rest_api.dto.PersonalInfoDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/personalInfo")
public class PersonalInfoController {
    private final PersonalInfoFacade personalInfoFacade;

    public PersonalInfoController(PersonalInfoFacade personalInfoFacade) {
        this.personalInfoFacade = personalInfoFacade;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public PersonalInfoDto createPersonalInfo(@Valid @RequestBody PersonalInfoDto personalInfoDto) {
        return personalInfoFacade.createPersonalInfo(personalInfoDto);
    }

    @GetMapping
    public PersonalInfoDto getPersonalInfoById(@AuthenticationPrincipal User user) {
        return personalInfoFacade.getPersonalInfo(user);

    }

    @PutMapping("/update")
    public PersonalInfoDto updatePersonalInfo(@AuthenticationPrincipal User user,
                                              @RequestBody PersonalInfoDto personalInfoDto) {
        return personalInfoFacade.updatePersonalInfo(user, personalInfoDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public PersonalInfoDto deletePersonalInfo(@PathVariable Long id) {
        return personalInfoFacade.deletePersonalInfo(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    public PersonalInfoDto getPersonalInfoByUserId(@PathVariable Long userId) {
        return personalInfoFacade.getPersonalInfoByUserId(userId);
    }
}
