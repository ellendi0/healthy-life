package com.webapp.app_rest_api.model.mapper;

import com.webapp.app_rest_api.dto.PersonalInfoDto;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoMapper {
    public PersonalInfo mapToPersonalInfo(PersonalInfo personalInfoToUpdate, PersonalInfo personalInfo){
        personalInfoToUpdate.setWeight(personalInfo.getWeight());
        personalInfoToUpdate.setHeight(personalInfo.getHeight());
        personalInfoToUpdate.setDateOfBirth(personalInfo.getDateOfBirth());
        personalInfoToUpdate.setGender(personalInfo.getGender());
        personalInfoToUpdate.setGoal(personalInfo.getGoal());
        personalInfoToUpdate.setActivity(personalInfo.getActivity());
        return personalInfoToUpdate;
    }

    public PersonalInfoDto mapToDto(PersonalInfo personalInfo){
        PersonalInfoDto personalInfoDto = new PersonalInfoDto();
        personalInfoDto.setWeight(personalInfo.getWeight());
        personalInfoDto.setHeight(personalInfo.getHeight());
        personalInfoDto.setDateOfBirth(personalInfo.getDateOfBirth());
        personalInfoDto.setGender(personalInfo.getGender());
        personalInfoDto.setGoal(personalInfo.getGoal());
        personalInfoDto.setActivity(personalInfo.getActivity());
        return personalInfoDto;
    }

    public PersonalInfo mapToEntity(PersonalInfoDto personalInfoDto){
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setWeight(personalInfoDto.getWeight());
        personalInfo.setHeight(personalInfoDto.getHeight());
        personalInfo.setDateOfBirth(personalInfoDto.getDateOfBirth());
        personalInfo.setGender(personalInfoDto.getGender());
        personalInfo.setGoal(personalInfoDto.getGoal());
        personalInfo.setActivity(personalInfoDto.getActivity());
        return personalInfo;
    }
}
