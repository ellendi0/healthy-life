package com.webapp.app_rest_api.service.impl;

import com.webapp.app_rest_api.exception.ResourceNotFoundException;
import com.webapp.app_rest_api.model.entities.PersonalInfo;
import com.webapp.app_rest_api.model.mapper.PostMapper;
import com.webapp.app_rest_api.model.entities.Post;
import com.webapp.app_rest_api.repository.PostRepository;
import com.webapp.app_rest_api.service.IPostService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;
    private final PostMapper mapper;

    public PostService(PostRepository postRepository, PostMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public Post getPost(PersonalInfo personalInfo, Long id) {
        return postRepository.findByPersonalInfo_IdAndId(personalInfo.getId(), id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
    }

    @Override
    public List<Post> getAllPost(PersonalInfo personalInfo) {
        return postRepository.findAll();
    }

    @Override
    public Post createPost(PersonalInfo personalInfo, Post post) {
        post.setPersonalInfo(personalInfo);
        post.setDate(Instant.now());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(PersonalInfo personalInfo, Long id, Post post) {
        Post postUpdated = getPost(personalInfo, id);
        return postRepository.save(mapper.map(post, postUpdated));
    }

    @Override
    public void deletePost(PersonalInfo personalInfo, Long id) {
        postRepository.delete(getPost(personalInfo, id));
    }

    @Override
    public void deleteAllPost(PersonalInfo personalInfo) {
        postRepository.deleteAllByPersonalInfo_Id(personalInfo.getId());
    }
}
