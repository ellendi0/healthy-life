package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
}
