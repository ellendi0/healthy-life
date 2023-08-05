package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.PersonalInfoDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.User;
import com.webapp.app_rest_api.model.mapper.PersonalInfoMapper;
import com.webapp.app_rest_api.service.impl.PersonalInfoService;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoFacade {
    private final PersonalInfoService personalInfoService;
    private final PersonalInfoMapper personalInfoMapper;

    public PersonalInfoFacade(PersonalInfoService personalInfoService, PersonalInfoMapper personalInfoMapper) {
        this.personalInfoService = personalInfoService;
        this.personalInfoMapper = personalInfoMapper;
    }

    public PersonalInfoDto createPersonalInfo(PersonalInfoDto personalInfoDto) {
        return personalInfoMapper.mapToDto(personalInfoService.createPersonalInfo(
                personalInfoMapper.mapToEntity(personalInfoDto)));
    }

    public PersonalInfoDto getPersonalInfo(User user) {
        return personalInfoMapper.mapToDto(personalInfoService.getPersonalInfo(user.getPersonalInfo()));
    }

    public PersonalInfoDto updatePersonalInfo(User user, PersonalInfoDto personalInfoDto) {
        return personalInfoMapper.mapToDto(personalInfoService.updatePersonalInfo(
                user.getPersonalInfo().getId(), personalInfoMapper.mapToEntity(personalInfoDto)));
    }

    public PersonalInfoDto deletePersonalInfo(Long id) {
        System.out.println("The personal info with id " + id + " has been deleted.");
        return personalInfoMapper.mapToDto(personalInfoService.deletePersonalInfo(id));
    }

    public PersonalInfoDto getPersonalInfoByUserId(Long id) {
        return personalInfoMapper.mapToDto(personalInfoService.getPersonalInfoByUserId(id));
    }
}
