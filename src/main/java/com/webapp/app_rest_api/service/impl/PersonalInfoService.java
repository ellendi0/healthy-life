package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.mapper.PersonalInfoMapper;
import com.webapp.app_rest_api.repository.PersonalInfoRepository;
import com.webapp.app_rest_api.service.IPersonalInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInfoService implements IPersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;
    private final PersonalInfoMapper mapper;
    private final DayDietService dayDietService;
    private final DietService dietService;

    public PersonalInfoService(PersonalInfoRepository personalInfoRepository,
                               PersonalInfoMapper mapper,
                               DayDietService dayDietService,
                               DietService dietService) {
        this.personalInfoRepository = personalInfoRepository;
        this.mapper = mapper;
        this.dayDietService = dayDietService;
        this.dietService = dietService;
    }

    @Override
    public PersonalInfo createPersonalInfo(PersonalInfo personalInfo) {
        personalInfoRepository.save(personalInfo);
        dayDietService.createUpdateDayDiet(personalInfo);
        personalInfo.setDiet(dietService.createCustomDiet(personalInfo));
        return personalInfoRepository.save(personalInfo);
    }

    @Override
    public PersonalInfo getPersonalInfo(PersonalInfo personalInfo) {
        return personalInfoRepository.findById(personalInfo.getId()).orElseThrow(()
                -> new ResourceNotFoundException("Personal Info", "id", String.valueOf(personalInfo.getId())));
    }

    @Override
    public List<PersonalInfo> getAllUserAccountNonLocked() {
        return personalInfoRepository.findAllByUserIsActive(true);
    }

    @Override
    public PersonalInfo updatePersonalInfo(Long id, PersonalInfo personalInfo) {
        PersonalInfo personalInfoUpdated = personalInfoRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Personal Info", "id", String.valueOf(id)));
        personalInfoRepository.save(mapper.mapToPersonalInfo(personalInfoUpdated, personalInfo));
        dietService.countDiet(personalInfoUpdated, personalInfoUpdated.getDiet());
        return personalInfoUpdated;
    }

    @Override
    public PersonalInfo deletePersonalInfo(Long id) {
        PersonalInfo personalInfo = personalInfoRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Personal Info", "id", String.valueOf(id)));
        personalInfoRepository.deleteById(id);
        return personalInfo;
    }

    @Override
    public PersonalInfo getPersonalInfoByUserId(Long userId) {
        return personalInfoRepository.findByUserId(userId).orElseThrow(()
                -> new ResourceNotFoundException("Personal Info", "userId", String.valueOf(userId)));
    }
}
