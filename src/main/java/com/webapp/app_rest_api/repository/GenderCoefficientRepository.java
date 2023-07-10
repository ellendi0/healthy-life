package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.GenderCoefficient;
import com.webapp.app_rest_api.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderCoefficientRepository extends JpaRepository<GenderCoefficient, Long> {
    Optional<GenderCoefficient> getGenderCoefficientByGender(Gender gender);
}
