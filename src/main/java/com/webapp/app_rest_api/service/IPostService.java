package com.webapp.app_rest_api.service;

import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.entities.Post;

import java.util.List;

public interface IPostService {
    Post getPost(PersonalInfo personalInfo, Long id);

    List<Post> getAllPost(PersonalInfo personalInfo);

    Post createPost(PersonalInfo personalInfo, Post post);

    Post updatePost(PersonalInfo personalInfo, Long id, Post post);

    void deletePost(PersonalInfo personalInfo, Long id);

    void deleteAllPost(PersonalInfo personalInfo);
}
