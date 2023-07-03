package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
    Optional<PersonalInfo> findByUserId(Long userId);

    @Query("SELECT p FROM PersonalInfo p WHERE p.user.isAccountNonLocked = ?1")
    List<PersonalInfo> findAllByUserIsActive(boolean userAccountNonLocked);
}
