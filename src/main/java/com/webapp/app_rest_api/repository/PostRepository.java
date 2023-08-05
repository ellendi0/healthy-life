package com.webapp.app_rest_api.repository;

import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPersonalInfo_IdAndId(Long personalInfoId, Long id);
    void deleteAllByPersonalInfo_Id(Long personalInfoId);
}
