package com.webapp.app_rest_api.model.mapper;

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
}
