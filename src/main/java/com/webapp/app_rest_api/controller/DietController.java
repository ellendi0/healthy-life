package com.webapp.app_rest_api.controller;

import com.webapp.app_rest_api.controller.facade.DietFacade;
import com.webapp.app_rest_api.dto.DietDto;
import com.webapp.app_rest_api.model.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/diet")
public class DietController {
    private final DietFacade dietFacade;

    public DietController(DietFacade dietFacade) {
        this.dietFacade = dietFacade;
    }

    @GetMapping
    public DietDto getDiet(@AuthenticationPrincipal User user){
        return dietFacade.getDiet(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createPersonalInfo")
    public DietDto createDiet(@RequestBody User user){
        return dietFacade.createDiet(user);
    }

    @PostMapping
    public DietDto createUpdateDiet(@AuthenticationPrincipal User user,
                                    @RequestBody DietDto dietDto){
        return dietFacade.createDiet(user, dietDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public DietDto getDietById(@PathVariable Long id) {
        return dietFacade.getDietById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/calories")
    public DietDto updateDiet(@AuthenticationPrincipal User user,
                              @RequestParam(value = "calories") Double calories){
        return dietFacade.updateDiet(user, calories);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete")
    public DietDto deleteDiet(@RequestParam Long id){
        return dietFacade.deleteDiet(id);
    }
}
