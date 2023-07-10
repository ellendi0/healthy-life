package com.webapp.app_rest_api.controller.facade;

import com.webapp.app_rest_api.dto.PersonalInfoDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
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

    public PersonalInfoDto getPersonalInfoById(Long id) {
        return personalInfoMapper.mapToDto(personalInfoService.getPersonalInfoById(id));
    }

    public PersonalInfoDto updatePersonalInfo(Long id, PersonalInfo personalInfo) {
        return personalInfoMapper.mapToDto(personalInfoService.updatePersonalInfo(id, personalInfo));
    }

    public PersonalInfoDto deletePersonalInfo(Long id) {
        return personalInfoMapper.mapToDto(personalInfoService.deletePersonalInfo(id));
    }

    public PersonalInfoDto getPersonalInfoByUserId(Long userId) {
        return personalInfoMapper.mapToDto(personalInfoService.getPersonalInfoByUserId(userId));
    }
}
